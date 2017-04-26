package org.stokesdrift.shores.generator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.util.DuneUtil;

public abstract class BaseGeneratorProvider implements GeneratorProvider {

	protected List<Generator> generators;
	protected Dune dune;
	protected AppInfo appInfo;
	
	
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


	public void fillOutDetails(AppInfo info, Dune dune)  {
		this.appInfo = info;
		this.dune = dune;
		
		if (null != dune.getDetails()) {
			DuneUtil.fillOutDetails(info, dune);
		}
	}
	
}
