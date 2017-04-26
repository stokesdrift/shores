package org.stokesdrift.shores.commands;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.Attribute.*;

import java.io.IOException;
import java.util.Map;

import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.parser.AggregatedModelParser;
import org.stokesdrift.shores.parser.ModelParser;
import org.stokesdrift.shores.util.ExceptionUtil;

import io.airlift.airline.Command;

@Command(name="list", description="List entities and status")
public class ListCommand extends BaseCommand {

	
	@Override
	public void run() {
		AnsiConsole.systemInstall();
		Map<String,Entity> entities = this.getEntities();
		for(Entity entity: entities.values()) {
			System.out.println( ansi().eraseScreen().fg(Color.DEFAULT).a(INTENSITY_BOLD).a(entity.getName()).reset());
		}	
		AnsiConsole.systemUninstall();
	}

	
	
}
