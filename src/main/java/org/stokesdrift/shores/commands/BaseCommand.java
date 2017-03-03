package org.stokesdrift.shores.commands;

import java.util.Map;

import org.stokesdrift.shores.config.Config;

import io.airlift.airline.Option;
import io.airlift.airline.OptionType;

public abstract class BaseCommand implements ShoreCommand {
	
	private Config config;
	private Map<String,String> options;

	
	@Option(type = OptionType.GLOBAL, name = "-c", description="Configuration file location")
	public String configFile;
	
	@Option(type = OptionType.GLOBAL, name = "-v", description = "Verbose mode")
    public boolean verbose;
	
	@Override
	public void setup(Map<String, String> options) {
		this.options = options;
	}

	public Config getConfig() {
		if(config == null) {
			// load up from the config file or default setup
		}
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	
	

}
