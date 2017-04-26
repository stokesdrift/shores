package org.stokesdrift.shores.parser;

import java.util.Map;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.model.DuneDetail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * {
  "name": "docker-generator",
  "version": "0.1.0",
  "description": "",
  "files": [
    "generators"
  ],
  "filenames": {
     "filename.ftl": "Hello${entity.name}.java",
  },
  "globals": [ "filenameglobal.ftl" ],
  "keywords": ["yeoman-generator"],
  "dependencies": {
    "yeoman-generator": "^1.0.0"
  }
}
 * @author driedtoast
 *
 */
public class YeomanDuneDetailParser extends BaseJsonParser implements DuneParser {

	public static final String FILE_NAME = "generator-definition.json";
	
	private DuneDetail detail;
	private Dune dune;
	private AppInfo appInfo;
	
	@Override
	public void handleRoot(JsonElement element) {
		detail = new DuneDetail();
		
		JsonObject jobj = element.getAsJsonObject();	
		JsonArray jarray = jobj.getAsJsonArray("globals");
		if(null != jarray) {
			for(JsonElement elem: jarray) {
				detail.getGlobalTemplates().add(elem.getAsString());
			}
		}
		
		JsonObject filenames = jobj.getAsJsonObject("filenames");
		for(Map.Entry<String, JsonElement> mapentry: filenames.entrySet()) {
			detail.getFileFormats().put(mapentry.getKey(), mapentry.getValue().getAsString());
		}
		
		dune.setDetails(detail);

	}

	@Override
	public void init(AppInfo info, Dune dune) {
		this.appInfo = info;
		this.dune = dune;
	}
	
	@Override
	public DuneDetail getDuneDetail() {
		return detail;
	}

}
