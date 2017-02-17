package org.stokesdrift.shores.model;

/**
 * Represents a property on an object or a parameter on an endpoint
 * 
 * @author driedtoast
 *
 */
public class Property {

	private boolean required = false;
	private String type;
	private String format;
	private String description;
	private String name;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
