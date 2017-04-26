package org.stokesdrift.shores.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Details of a dun / generator setup 
 * 
 * @author driedtoast
 *
 */
public class DuneDetail {

	
	private Map<String, String> fileNameFormats = new HashMap<String, String>();
	private List<RunMode> supportedModes = Lists.newArrayList(RunMode.CREATE);
	private Set<String> globalTemplates = Sets.newHashSet();
	
	/**
	 * Returns a map of template file name to generated file format with tokens for naming
	 * 
	 */
	public Map<String,String> getFileFormats() {
		return fileNameFormats;
	}
	
	

	public List<RunMode> getSupportedModes() {
		return supportedModes;
	}
	public void setSupportedModes(List<RunMode> supportedModes) {
		this.supportedModes = supportedModes;
	}



	public Set<String> getGlobalTemplates() {
		return globalTemplates;
	}



	public void setGlobalTemplates(Set<String> globalTemplates) {
		this.globalTemplates = globalTemplates;
	}	
	
	
	
}
