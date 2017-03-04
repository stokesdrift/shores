package org.stokesdrift.shores.model;

import java.util.List;

/**
 * Header info
 * 
 * @author dmarchant
 *
 */
public class AppInfo {

	private String name;
	private String description;
	private List<String> keywords;
	private String image;
	private List<Dune> dunes;
	private String logo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Dune> getDunes() {
		return dunes;
	}

	public void setDunes(List<Dune> dunes) {
		this.dunes = dunes;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
