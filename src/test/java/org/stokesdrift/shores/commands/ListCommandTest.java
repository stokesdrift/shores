package org.stokesdrift.shores.commands;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.stokesdrift.shores.Shores;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.util.FileUtil;

import io.airlift.airline.Cli;

public class ListCommandTest {

	@Test
	public void testBasicList() throws Exception {
		
		String fileName = FileUtil.getFilenameFromClassfile("examples/todos/app.json");

		String[] args = new String[] { "-a", fileName, "list"};
		Shores shores = new Shores(args);
		Cli<Runnable> cli = shores.createRunner();
		Runnable runnable =  cli.parse( args );
		Assert.assertNotNull(runnable);
		
		ListCommand listCommand = (ListCommand)runnable;
		Assert.assertNotNull(listCommand.getAppInfo());
		Map<String,Entity> entities = listCommand.getEntities();
		Assert.assertNotNull(entities);
		Entity entity = entities.get("Pet");
		Assert.assertNotNull(entity);
	}
	
}
