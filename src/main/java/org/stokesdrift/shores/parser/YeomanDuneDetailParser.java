package org.stokesdrift.shores.parser;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.model.DuneDetail;

import com.google.gson.JsonElement;

/**
 * {
  "name": "docker-generator",
  "version": "0.1.0",
  "description": "",
  "files": [
    "generators"
  ],
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
		
		// TODO parse it
		
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
