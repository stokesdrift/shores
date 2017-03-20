package org.stokesdrift.shores.generator.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

public class FreeMarkerGenerator implements Generator {

	private Configuration configuration;
	private boolean global;
	private File templateName;
	
	public FreeMarkerGenerator(Configuration configuration, File templateName) {
		this(configuration, templateName, false);
	}
	
	public FreeMarkerGenerator(Configuration configuration, File templateName, boolean isGlobal) {
		this.configuration = configuration;
		this.global = isGlobal;
		this.templateName = templateName;
	}
	
	
	@Override
	public boolean isGlobal() {
		return this.global;
	}

	@Override
	public void generateEntity(AppInfo info, Entity entity) {
		// TODO need to do some configuration based on template name
		Template template = this.getTemplate();
		// File output, need to create a file / directory structure
		
		// TODO create file name based on template path + entityName approach
		// TODO need to get info from the dune
		Writer out = new OutputStreamWriter(System.out);
		Map<String,Object> root = new HashMap<String,Object>();
		
		root.put("entity", entity);
		root.put("appinfo", info);
		
		try {
			template.process(root, out);
		} catch (Exception e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

	@Override
	public void generateGlobal(AppInfo info) {
		
	}
	
	
	protected Template getTemplate() {		
		try {
			return configuration.getTemplate(templateName.getPath() + File.separator + templateName.getName());
		} catch (Exception  e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

}
