package org.stokesdrift.shores.parser;

import java.io.IOException;

import org.stokesdrift.shores.model.AppInfo;

public interface AppInfoParser {

	public void parseFile(String file)  throws IOException;	
	
	public AppInfo getAppInfo();
		
}
