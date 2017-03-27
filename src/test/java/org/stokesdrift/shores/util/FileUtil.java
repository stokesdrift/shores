package org.stokesdrift.shores.util;

import java.net.URL;

public class FileUtil {
	
	public static String getFilenameFromClassfile(String path) throws Exception {
		URL url = FileUtil.class.getClassLoader().getResource(path);
		String fileName = url.toURI().toString();
		fileName = fileName.replaceAll("file:", "");
		return fileName;
	}

}
