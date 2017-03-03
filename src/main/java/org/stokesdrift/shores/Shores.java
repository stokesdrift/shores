package org.stokesdrift.shores;

import java.util.HashMap;
import java.util.Map;

import org.stokesdrift.shores.commands.CreateCommand;
import org.stokesdrift.shores.commands.SetupCommand;
import org.stokesdrift.shores.commands.ShoreCommand;

import io.airlift.airline.Cli;
import io.airlift.airline.Cli.CliBuilder;
import io.airlift.airline.Help;

public class Shores implements Runnable {

	private String[] args;
	
	public static void main(String[] args) {
		Shores shores = new Shores(args);
		shores.run();

		// 2. find template directory
		// 3. load templates based on generator definition? 
				// - file naming convention with model references pointing to template
				// - output pathing?
				// - support for system / command line options
				// - general build / project configuration
		// 4. create directories based on generator definition
		// 5. create files based on iterating over the models and using generator definition as a base
			// - support generating based on new model name, make sure not to override if already exists
	}
	
	
	public Shores(String[] args) {
		this.args = args;
	}
	
	public Cli<Runnable> createRunner() {
		
		CliBuilder<Runnable> builder = Cli.<Runnable>builder(this.getClass().getName().toLowerCase())
			.withDescription("Code generator")
			.withDefaultCommand(Help.class)
			.withCommands(SetupCommand.class, CreateCommand.class);
				
		return builder.build();
	}
	
	
	public void run() {
		Cli<Runnable> cli = this.createRunner();
		Runnable runnable = cli.parse(args);
		
		Map<String,String> options = new HashMap<String,String>();
		// TODO add options from command line
		
		if(runnable instanceof ShoreCommand) {
			ShoreCommand command = (ShoreCommand)runnable;
			
			command.setup(options);
		}		
		runnable.run();	
	}
	
			
}
