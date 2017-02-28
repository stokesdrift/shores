package org.stokesdrift.shores.parser;

import java.util.Map;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Entity;

public interface ModelParser {

	public void parseFile(String file);	
	
	public AppInfo getAppInfo();
	
	public Map<String,Entity> getEntities();
}
