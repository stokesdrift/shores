package org.stokesdrift.shores.parser;

import java.io.IOException;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.model.DuneDetail;

public interface DuneParser {
	
	void parseFile(String file)  throws IOException;
	
	void init(AppInfo info, Dune dune);

	DuneDetail getDuneDetail();
}
