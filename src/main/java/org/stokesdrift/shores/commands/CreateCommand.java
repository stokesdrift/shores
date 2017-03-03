package org.stokesdrift.shores.commands;

import java.util.List;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;

@Command(name="create", description="Create a new set files based on object definition in config, pass name to filter down to name")
public class CreateCommand extends BaseCommand {

    @Arguments(description = "Names of entities to create")
    public List<String> names;
	
	@Override
	public void run() {
		// TODO create based on data definition
		
	}

	
	
}
