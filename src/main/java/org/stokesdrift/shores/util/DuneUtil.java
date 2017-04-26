package org.stokesdrift.shores.util;

import java.io.File;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.parser.DuneParser;
import org.stokesdrift.shores.parser.YeomanDuneDetailParser;

public class DuneUtil {

	public static String getFullLocation(AppInfo info, Dune dune) {
		String fullLocation = info.getBasePath() + File.separator + Dune.DUNE_DIRECTORY + File.separator + dune.getLocation();
		dune.setFullPath(fullLocation); 		
		return fullLocation;
	}

	public static void fillOutDetails(AppInfo info, Dune dune) {
		DuneParser parser = null;
		String generatorFile = null;
		// Check file and get right parser
		if(existsFor(YeomanDuneDetailParser.FILE_NAME, dune)) {
			parser = new YeomanDuneDetailParser();
			generatorFile = YeomanDuneDetailParser.FILE_NAME;
		}
		
		// Parse details
		if(null != parser) {
			parser.init(info, dune);
			String fileName = dune.getFullPath() + File.separator + generatorFile;
			try {
				parser.parseFile(fileName);
			} catch (Exception e) {
				throw ExceptionUtil.unchecked(e);
			}
		}			
	}
	
	public static boolean existsFor(String fileName, Dune dune) {
		File file = new File(dune.getFullPath() + File.separator + fileName);
		return file.exists();
	}
	
}
