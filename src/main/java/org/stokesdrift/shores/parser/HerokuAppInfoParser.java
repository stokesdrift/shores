package org.stokesdrift.shores.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.util.ExceptionUtil;
import org.stokesdrift.shores.util.JsonUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**

{
	  "name": "Todoapp",
	  "description": "This is an app to illustrate todo app generation",
	  "keywords": [
	    "productivity",
	    "HTML5",
	    "todo"
	  ],
	  "website": "https://example.com/",
	  "repository": "https://github.com/stokesdrift/shores-todo",
	  "logo": "https://example.com/logo.svg",


}

"env": {
    "SECRET_TOKEN": {
      "description": "A secret key for verifying the integrity of signed cookies.",
      "generator": "secret"
    },
    "WEB_CONCURRENCY": {
      "description": "The number of processes to run.",
      "value": "5"
    }
  },
  "image": "docker/ruby",  
  "environments": {
    "test": {
      "scripts": {
        "test": "bundle exec rake test"
      }
    }
  },
  "rootdirs" : {
    "ext":"src/ext"
  },
  "dunes": [
     { 
        "name": "docker",
        "location": "./docker-generator",
        "overrides":{
           "config-key1":"overridden value 1"
        }
     }
  ]
  **/
public class HerokuAppInfoParser extends BaseJsonParser implements AppInfoParser {

	@Override
	public void handleRoot(JsonElement element) {
		JsonObject allData = element.getAsJsonObject();
		AppInfo appInfo = new AppInfo();
		appInfo.setName(JsonUtil.getAsString(allData, "name"));
		appInfo.setDescription(JsonUtil.getAsString(allData, "description"));
		appInfo.setLogo(JsonUtil.getAsString(allData, "logo"));
		appInfo.setImage(JsonUtil.getAsString(allData, "image"));
		this.setAppInfo(appInfo);
		this.parseDunes(appInfo, allData.getAsJsonArray("dunes"));
		this.parseRootDirs(appInfo, allData.getAsJsonObject("rootdirs"));
		appInfo.setBasePath(basePath);
		
		// TODO parse environment variables
		// TODO parse keywords and add sites
		
		
	}
	
	/**
	 * Used for global defaults for all the source dirs based on file extension 
	 * 
	 */
	protected void parseRootDirs(AppInfo appInfo, JsonObject obj) {
		if(null == obj) {
			return;
		}
		
		Iterator<Map.Entry<String,JsonElement>> iter = obj.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, JsonElement> element = iter.next();
			appInfo.getRootDirs().put(element.getKey(), element.getValue().getAsString());
		}
		
	}
	
	protected void parseDunes(AppInfo appInfo, JsonArray array) {
		if(null == array) {
			return;
		}
		List<Dune> dunes = appInfo.getDunes();
		if(null == dunes) {
			dunes = new ArrayList<Dune>();
		}
		
		final List<Dune> duneList = dunes; 
		array.forEach( element -> {
			JsonObject obj = element.getAsJsonObject();
			Dune dune = new Dune();
			dune.setLocation(JsonUtil.getAsString(obj, "location"));
			dune.setName(JsonUtil.getAsString(obj, "name"));
			JsonObject overrides = obj.getAsJsonObject("overrides");
			
			Map<String,Object> values = overrides.entrySet().stream().collect(
					Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getAsString())					
					);
			
			dune.setOverrides(values);
			duneList.add(dune);
		});
		appInfo.setDunes(dunes);
	}
	
	

}
