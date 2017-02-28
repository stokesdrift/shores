package org.stokesdrift.shores.parser;

import java.util.HashMap;
import java.util.Map;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Entity;

public abstract class BaseParser implements ModelParser {

	private AppInfo appInfo;
	private Map<String, Entity> entities;

	@Override
	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	@Override
	public Map<String, Entity> getEntities() {
		return entities;
	}

	public void setEntities(Map<String, Entity> entities) {
		this.entities = entities;
	}
	
	
	public void addEntity(Entity entity) {
		if (null == this.entities) {
			this.entities = new HashMap<String, Entity>();
		}
		this.entities.put(entity.getName(), entity);
	}

}
