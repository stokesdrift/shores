package org.stokesdrift.shores;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.stokesdrift.shores.commands.CreateCommand;

import io.airlift.airline.Cli;

public class ShoresTest {
	
	@Test
	public void testArgumentParsing() throws Exception { 
		String[] args = new String[] {"create", "blah"};
		Shores shores = new Shores(args);
		Cli<Runnable> cli = shores.createRunner();
		Runnable runnable =  cli.parse( args );
		
		Assert.assertNotNull(runnable);
		Assert.assertTrue(runnable instanceof CreateCommand);
		
		CreateCommand scommand = (CreateCommand)runnable;
		Assert.assertNotNull(scommand.names);
		Optional<String> optional = scommand.names.stream().findFirst();
		String name = optional.get();
		Assert.assertEquals("blah", name);
	}
	
}
