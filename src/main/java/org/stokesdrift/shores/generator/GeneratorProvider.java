package org.stokesdrift.shores.generator;

import java.io.IOException;
import java.util.List;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;

/**
 * Manages and provides generators for a given app and Dune setup
 * 
 * @author driedtoast
 *
 */
public interface GeneratorProvider {
	
	void init(AppInfo info, Dune dune) throws IOException;

	List<Generator> getEntityGenerators();
	
	List<Generator> getGlobalGenerators();
}
