package org.stokesdrift.shores.parser;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.util.JsonUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
		
		// TODO parse dunes
		// TODO parse environment variables
		// TODO parse keywords and add sites
		
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
		
	}

}
