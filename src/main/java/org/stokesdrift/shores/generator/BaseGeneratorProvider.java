package org.stokesdrift.shores.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.parser.DuneParser;
import org.stokesdrift.shores.parser.YeomanDuneDetailParser;
import org.stokesdrift.shores.util.ExceptionUtil;

public abstract class BaseGeneratorProvider implements GeneratorProvider {

	protected List<Generator> generators;
	
	public static final String DUNE_DIRECTORY = "dunes"; 
	
	
	public abstract void init(AppInfo info, Dune dune) throws IOException;
	
	@Override
	public List<Generator> getEntityGenerators() {
		if(null == generators) {
			return null;
		}
		return generators.stream().filter(gen -> !gen.isGlobal()).collect(Collectors.toList());
	}

	@Override
	public List<Generator> getGlobalGenerators() {
		if(null == generators) {
			return null;
		}
		return generators.stream().filter(gen -> gen.isGlobal()).collect(Collectors.toList());
	}

	
	protected String getFullLocation(AppInfo info, Dune dune) {
		String fullLocation = info.getBasePath() + File.separator + DUNE_DIRECTORY + File.separator + dune.getLocation();
		dune.setFullPath(fullLocation); 		
		return fullLocation;
	}

	protected void fillOutDetails(AppInfo info, Dune dune) {		
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
	
	protected boolean existsFor(String fileName, Dune dune) {
		File file = new File(dune.getFullPath() + File.separator + fileName);
		return file.exists();
	}
	
}
