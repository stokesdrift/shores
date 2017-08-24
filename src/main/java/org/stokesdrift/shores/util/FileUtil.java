package org.stokesdrift.shores.util;

public class FileUtil {

	
	public static String getExtension(String fileName) {
		if(null == fileName) {
			return null;			
		}
		
		int idx = fileName.lastIndexOf('.');
		if(idx != -1) {
			return fileName.substring(idx+1);
		}
		return null;
		
	}

	
	
	
}
