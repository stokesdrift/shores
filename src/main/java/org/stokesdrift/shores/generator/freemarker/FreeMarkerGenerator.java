package org.stokesdrift.shores.generator.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.stokesdrift.shores.generator.Generator;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.util.ExceptionUtil;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class FreeMarkerGenerator implements Generator {

	private Configuration configuration;
	private boolean global;
	private File templateName;
	private OutputStream out = null; 
	private String outputNameFormat = null;
	
	
	public FreeMarkerGenerator(Configuration configuration, File templateName, String outputNameFormat) {
		this(configuration, templateName, false, outputNameFormat);
	}
	
	public FreeMarkerGenerator(Configuration configuration, File templateName, boolean isGlobal, String outputNameFormat) {
		this.configuration = configuration;
		this.global = isGlobal;
		this.templateName = templateName;
		this.outputNameFormat = outputNameFormat;
	}
	
	
	@Override
	public boolean isGlobal() {
		return this.global;
	}
	
	public void setOutputStream(OutputStream outParam) {
		this.out = outParam;
	}
	
	public OutputStream getOutputStream(AppInfo info, Entity entity, Map<String,Object> context) {
		if (null != out) {
			return out;
		}
		try {
			Template t = new Template("filename", new StringReader(this.outputNameFormat),
			               new Configuration(Configuration.getVersion()));
			StringWriter writer = new StringWriter();
			t.process(context, writer);
			String fileName = writer.toString();
			fileName = info.getBasePath() + File.separatorChar + fileName; 
			File file = new File(fileName);
			file.getParentFile().mkdirs();
			if(file.getParentFile().exists()) {
				if(!file.exists()) {
					file.createNewFile();
				}
				System.out.println(" TEST " + file);
				out = new FileOutputStream(file);
			}
			
			// TODo create a file and render to it
		} catch (Exception e) {
			// TODO should i fail or keep going
			ExceptionUtil.unchecked(e);
		}
		// TODO create file name based on template path + entityName approach
		// TODO need to get info from the dune
		if(null == out) {
			out = System.out;
		}
		return out;		
		
	}

	@Override
	public void generateEntity(AppInfo info, Entity entity) {
		// TODO need to do some configuration based on template name
		Template template = this.getTemplate();
	    Map<String,Object> root = new HashMap<String,Object>();
		
		root.put("entity", entity);
		root.put("appinfo", info);
		// File output, need to create a file / directory structure
		
		// TODO create file name based on template path + entityName approach
		// TODO need to get info from the dune
		
		Writer out = new OutputStreamWriter(this.getOutputStream(info, entity, root));
	
		
		try {
			template.process(root, out);
			out.close();
			out = null;
		} catch (Exception e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

	@Override
	public void generateGlobal(AppInfo info) {
		
	}
	
	
	protected Template getTemplate() {		
		try {
			return configuration.getTemplate(templateName.getName());
		} catch (Exception  e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

}
