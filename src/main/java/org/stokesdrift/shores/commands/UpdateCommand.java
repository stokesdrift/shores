package org.stokesdrift.shores.commands;

import java.util.List;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;

@Command(name="update", description="Update a set files based on object definition in config, pass name to filter down to name")
public class UpdateCommand extends BaseCommand {

	
	@Arguments(description = "Names of entities to create")
    public List<String> names;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
