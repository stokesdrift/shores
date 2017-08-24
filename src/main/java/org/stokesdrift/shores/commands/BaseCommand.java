package org.stokesdrift.shores.commands;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.fusesource.jansi.Ansi.Color;
import org.stokesdrift.shores.config.Config;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.parser.AggregatedModelParser;
import org.stokesdrift.shores.parser.AppInfoParser;
import org.stokesdrift.shores.parser.HerokuAppInfoParser;
import org.stokesdrift.shores.parser.ModelParser;
import org.stokesdrift.shores.util.DuneUtil;
import org.stokesdrift.shores.util.ExceptionUtil;

import io.airlift.airline.Option;
import io.airlift.airline.OptionType;

public abstract class BaseCommand implements ShoreCommand {
	
	private Config config;
	private AppInfo appInfo;
	private Map<String,String> options;

	public static final String DEFAULT_APP_FILE = "app.json";
	
	
	@Option(type = OptionType.GLOBAL, name = "-c", description="Configuration file location")
	public String configFile;
	
	@Option(type = OptionType.GLOBAL, name = "-a", description="Application configuration file location")
	public String applicationConfigFile;
	
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

	public AppInfo getAppInfo()  {
		if(appInfo == null) {			
			if(null == applicationConfigFile) {
				this.applicationConfigFile = DEFAULT_APP_FILE;
			}
			AppInfoParser parser = new HerokuAppInfoParser();
			try {
				parser.parseFile(this.applicationConfigFile);
				appInfo = parser.getAppInfo(); 
			} catch (IOException e) {
				ExceptionUtil.unchecked(e);
			}
		}
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}
	
	
	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	
	public Map<String,Entity> getEntities() {
		ModelParser modelParser = new AggregatedModelParser(this.getAppInfo());
		try {
			modelParser.parseFile(null);
		} catch (IOException e) {
			ExceptionUtil.unchecked(e);
		}
		Map<String,Entity> entities = modelParser.getEntities();
		return entities;
	}
	
	
	public void washupToDunes(AppInfo info) {
		List<Dune> dunes = info.getDunes();
		if(null != dunes) {
			for(Dune dune: dunes) {
				System.out.println( ansi().fg(Color.DEFAULT).a("Loading dune: ").a(INTENSITY_BOLD).a(dune.getName()).reset());
				DuneUtil.getFullLocation(info, dune);
				DuneUtil.fillOutDetails(info, dune);
			}
		}
	}

}
