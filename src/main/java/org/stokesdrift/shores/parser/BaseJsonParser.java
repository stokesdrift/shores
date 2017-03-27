package org.stokesdrift.shores.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class BaseJsonParser extends BaseParser {

	protected String basePath;
	
	@Override
	public final void parseFile(String file) throws IOException {
	
		JsonParser parser = new JsonParser();
		File fileObj = new File(file);
		if(!fileObj.exists()) {
			return;
		}
		
		
		basePath =fileObj.getParentFile().getAbsolutePath();
		Reader reader = new FileReader(fileObj);
		JsonElement element = parser.parse(reader);
		this.handleRoot(element);

	}

	public abstract void handleRoot(JsonElement element);
	
	
	
	
}
