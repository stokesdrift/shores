package org.stokesdrift.shores.parser;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Entity;

public class AggregatedModelParser implements ModelParser {

	private AppInfo appInfo;
	private Map<String, Entity> entities;

	public static final String MODEL_DIRECTORY = "models"; 
	
	public AggregatedModelParser(AppInfo appInfo) {
		this.appInfo = appInfo;
	}
	
	@Override
	public void parseFile(String file) throws IOException {
		String modelDirectory = appInfo.getBasePath() + File.separator + MODEL_DIRECTORY;
		
		Collection<File> files = FileUtils.listFiles(new File(modelDirectory), new String[] { "json" }, true);	
		
		entities = new HashMap<String,Entity>();
		for(File modelFile: files) {
			Map<String,Entity> modelEntities = parseModelFile(modelFile);
			entities.putAll(modelEntities);
		}
	}

	public Map<String,Entity> parseModelFile(File file) throws IOException {
		SwaggerParser parser = new SwaggerParser();
		parser.parseFile(file.getAbsolutePath());
		Map<String,Entity> entities = parser.getEntities();
		return entities;
	}
	
	@Override
	public AppInfo getAppInfo() {
		return appInfo;
	}
		

	@Override
	public Map<String, Entity> getEntities() {
		return entities;
	}

}
