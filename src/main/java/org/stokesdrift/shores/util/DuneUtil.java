package org.stokesdrift.shores.util;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;

import java.io.File;

import org.fusesource.jansi.Ansi.Attribute;
import org.fusesource.jansi.Ansi.Color;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.parser.DuneParser;
import org.stokesdrift.shores.parser.YeomanDuneDetailParser;

public class DuneUtil {

	public static String getFullLocation(AppInfo info, Dune dune) {
		if(dune.getFullPath() != null) {
			return dune.getFullPath();
		}
		String fullLocation = info.getBasePath() + File.separator + Dune.DUNE_DIRECTORY + File.separator + dune.getLocation();
		dune.setFullPath(fullLocation); 		
		return fullLocation;
	}

	public static void fillOutDetails(AppInfo info, Dune dune) {
		String fullPath = getFullLocation(info, dune);
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
			String fileName = fullPath + File.separator + generatorFile;
			try {
				System.out.println( ansi().fg(Color.DEFAULT).a("Loading dune :").a(INTENSITY_BOLD).a(dune.getName()).reset().a(" from file name: ").a(Attribute.ITALIC).a(fileName).reset());
				parser.parseFile(fileName);
			} catch (Exception e) {
				throw ExceptionUtil.unchecked(e);
			}
		}			
	}
	
	public static boolean existsFor(String fileName, Dune dune) {
		System.out.println( ansi().fg(Color.DEFAULT).a("Checking for file:").a(Attribute.ITALIC).a(dune.getFullPath() + File.separator + fileName).reset());
		
		File file = new File(dune.getFullPath() + File.separator + fileName);
		return file.exists();
	}
	
}
