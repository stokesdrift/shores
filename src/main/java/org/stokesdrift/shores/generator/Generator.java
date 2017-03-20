package org.stokesdrift.shores.generator;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Entity;

public interface Generator {

	
	boolean isGlobal();
	
	void generateEntity(AppInfo info, Entity entity);
	
	void generateGlobal(AppInfo info);
	
	
	
}
