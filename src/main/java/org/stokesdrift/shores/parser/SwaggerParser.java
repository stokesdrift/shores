package org.stokesdrift.shores.parser;

import java.util.Map;
import java.util.Set;

import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.model.Property;
import org.stokesdrift.shores.util.JsonUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SwaggerParser extends BaseJsonParser {

	@Override
	public void handleRoot(JsonElement element) {
		JsonObject object = element.getAsJsonObject();
		JsonElement definitions = object.get("definitions");
		JsonObject defObj = definitions.getAsJsonObject();
		Set<Map.Entry<String,JsonElement>> entrySet = defObj.entrySet();
		for(Map.Entry<String, JsonElement> entry : entrySet) {
			this.processEntityElement(entry);
		}
			
	}

	/**
	 * Variations: 
	 * Simple:
	 * 
	 * "NewPet": {
      "type": "object",
      "required": [
        "name"
      ],
      "properties": {
        "name": {
          "type": "string"
        },
        "tag": {
          "type": "string"
        }
      }
    }
    
	 *
	 * 
	 * More complex:
	 * "Pet": {
      "type": "object",
      "allOf": [
        {
          "$ref": "#/definitions/NewPet"
        },
        {
          "required": [
            "id"
          ],
          "properties": {
            "id": {
              "type": "integer",
              "format": "int64"
            }
          }
        }
      ]
    },
	 */
	public void processEntityElement(Map.Entry<String, JsonElement> entry) {
		Entity entity = new Entity();
		entity.setName(entry.getKey());
		JsonElement elem = entry.getValue();
		JsonObject baseObjDef = elem.getAsJsonObject();
		JsonArray allOf = baseObjDef.getAsJsonArray("allOf");
		JsonObject properties = null;
		JsonArray required = null;
		if(allOf != null && allOf.size() > 1) {
			// TODO deal with references
			JsonObject allOfList = allOf.get(1).getAsJsonObject();
			properties = allOfList.getAsJsonObject("properties");
			required = allOfList.getAsJsonArray("required");
		} else {
			properties = baseObjDef.getAsJsonObject("properties");
			required = baseObjDef.getAsJsonArray("required");
		}
		
		if(properties != null) {
			Set<Map.Entry<String,JsonElement>> entrySet = properties.entrySet();
			
			for(Map.Entry<String, JsonElement> elemEntry : entrySet) {
				JsonObject propertyObj = elemEntry.getValue().getAsJsonObject();
				Property property = new Property();
				property.setName(elemEntry.getKey());
				property.setFormat(JsonUtil.getAsString(propertyObj, "format"));
				property.setType(JsonUtil.getAsString(propertyObj, "type"));
				
				if(required != null) {
					property.setRequired(JsonUtil.contains(required, elemEntry.getKey()));
				}
				entity.addProperty(property);				
			}
		}
		
		
		this.addEntity(entity);
		
		
	}
	
	
	
	
		// 1. load up the models from the open specification format
		/**
		 * "version": "1.0.0",
    "title": "Swagger Petstore",
    "description": "A sample API that uses a petstore as an example to demonstrate features in the swagger-2.0 specification",
    "termsOfService": "http://swagger.io/terms/",
    "contact": {
      "name": "Swagger API Team"
    },
    "license": {
      "name": "MIT"
    } */
	

}
