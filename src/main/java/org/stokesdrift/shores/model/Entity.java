package org.stokesdrift.shores.model;

import java.util.HashMap;
import java.util.Map;

public class Entity {

	private String name;
	private Map<String, Property> properties;
	private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addProperty(Property property) {
		if(null == properties) {
			properties = new HashMap<String, Property>();
		}
		properties.put(property.getName(), property);
	}

	public Property getProperty(String name) {
		return properties.get(name);
	}

}
