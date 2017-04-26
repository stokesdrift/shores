package org.stokesdrift.shores.generator.freemarker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.stokesdrift.shores.generator.BaseGeneratorProvider;
import org.stokesdrift.shores.generator.Generator;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.util.DuneUtil;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerGeneratorProvider extends BaseGeneratorProvider {

	private Configuration configuration;
	
	
	@Override
	public void init(AppInfo info, Dune dune) throws IOException {
		configuration = new Configuration(Configuration.VERSION_2_3_25);
		
		// TODO make details useful 
		this.fillOutDetails(info, dune);
		File directory = new File(DuneUtil.getFullLocation(info, dune));
		configuration.setDirectoryForTemplateLoading(directory);

		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);		
		this.setupGenerators(directory);
	}
	
	
	protected void setupGenerators(File directory) {
		Collection<File> files = FileUtils.listFiles(directory, new String[] { "ftl" }, true);		
		generators = new ArrayList<Generator>();
		
		
		Iterator<File> iter = files.iterator();
		while(iter.hasNext()) {
			File file = iter.next();
			System.out.println("generating using: " + file);
			String outputFileName = this.dune.getDetails().getFileFormats().get(file.getName());
			boolean global = this.dune.getDetails().getGlobalTemplates().contains(file.getName());
			FreeMarkerGenerator generator = new FreeMarkerGenerator(this.configuration, file, global, outputFileName);
			this.generators.add(generator);
		}
		
	}
	
	
}
