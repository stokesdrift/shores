package org.stokesdrift.shores.util;

import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
	
	
	public static boolean contains(JsonArray array, String key) {
		boolean cond = false;
		if(array != null && key != null) {
			Iterator<JsonElement> elements = array.iterator();
			while(elements.hasNext()) {
				JsonElement elem = elements.next();
				String value = elem.getAsString();
				if(key.equals(value)) {
					return true;
				}
			}						
		}
		return cond;
	}
	
	public static String getAsString(JsonObject element, String key) {
		JsonElement elementString = element.get(key);
		if(null != elementString) {
			return elementString.getAsString();
		}
		return null;
	}

}
