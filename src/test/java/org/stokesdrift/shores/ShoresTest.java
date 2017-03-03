package org.stokesdrift.shores;

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
	}
	
}
