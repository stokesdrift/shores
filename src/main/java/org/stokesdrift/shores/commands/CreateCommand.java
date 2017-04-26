package org.stokesdrift.shores.commands;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Attribute.INTENSITY_BOLD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.model.Dune;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.model.RunMode;
import org.stokesdrift.shores.util.DuneUtil;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;
import org.stokesdrift.shores.generator.*;
import org.stokesdrift.shores.generator.freemarker.FreemarkerGeneratorProvider;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;

@Command(name="create", description="Create a new set files based on object definition in config, pass name to filter down to name")
public class CreateCommand extends BaseCommand {

    public GeneratorProvider generatorProvider;

	@Arguments(description = "Names of entities to create")
	public List<String> names;
    
	@Override
	public void run() {
		AnsiConsole.systemInstall();
		
		Map<String,Entity> entities = this.getEntities();
		
		List<Entity> entityList = new ArrayList<Entity>();
		if (null != names && names.size() > 0) {
			for(String name: names) {
			  Entity entity = entities.get(name);
			  if(null != entity) {
			    entityList.add(entity);
			  }
			}
		} else {
			entityList.addAll(entities.values());
		}
		
		AppInfo appInfo = this.getAppInfo();
		this.washupToDunes(appInfo);
		
		for(Dune dune : appInfo.getDunes() ) {
			if(null == dune.getDetails() || 
					!dune.getDetails().getSupportedModes().contains(RunMode.CREATE)) {
				continue;
			}
			System.out.println( ansi().eraseScreen().fg(Color.DEFAULT).a("Runninng dune: ").a(INTENSITY_BOLD).a(dune.getName()).reset());
			
			generatorProvider = new FreemarkerGeneratorProvider();
			try {
				generatorProvider.init(appInfo, dune);
				this.runGenerators(generatorProvider, entityList);				
			} catch (IOException e) {
				System.out.println( ansi().eraseScreen().fg(Color.RED).a("Dune failed: " + e.getMessage()).a(INTENSITY_BOLD).a(dune.getName()).reset());					
			}
			
			
		}
		AnsiConsole.systemUninstall();
	}
	
	public void washupToDunes(AppInfo info) {
		List<Dune> dunes = info.getDunes();
		if(null != dunes) {
			for(Dune dune: dunes) {
				DuneUtil.fillOutDetails(info, dune);
			}
		}
	}

	
	public void runGenerators(GeneratorProvider provider, List<Entity> entities) {
		// TODO optimize with threads and such
		List<Generator> generators = provider.getEntityGenerators();
		for(Generator generator : generators) {
			for(Entity entity: entities) {
				System.out.println( ansi().eraseScreen().fg(Color.DEFAULT).a("Generating: ").a(INTENSITY_BOLD).a(entity.getName()).reset());				
				generator.generateEntity(getAppInfo(), entity);
			}
		}
	}
	
}
