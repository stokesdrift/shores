package org.stokesdrift.shores.model;

import java.util.Map;

public class Dune {

	private String name;
	private String location;
	private Map<String,Object> overrides;
	private String fullPath;
	
	private DuneDetail details;
	
	
	public DuneDetail getDetails() {
		return details;
	}
	public void setDetails(DuneDetail details) {
		this.details = details;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Map<String, Object> getOverrides() {
		return overrides;
	}
	public void setOverrides(Map<String, Object> overrides) {
		this.overrides = overrides;
	}
	
	
}
