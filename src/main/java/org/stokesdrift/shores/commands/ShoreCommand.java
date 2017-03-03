package org.stokesdrift.shores.commands;

import java.util.Map;

import org.stokesdrift.shores.config.Config;

public interface ShoreCommand extends Runnable {
	
	public void setup(Map<String,String> options);
	
	public Config getConfig();
}
