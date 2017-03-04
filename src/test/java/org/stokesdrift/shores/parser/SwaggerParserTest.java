package org.stokesdrift.shores.parser;

import java.net.URL;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.stokesdrift.shores.model.Entity;
import org.stokesdrift.shores.model.Property;
import org.stokesdrift.shores.parser.SwaggerParser;

public class SwaggerParserTest {

	
	@Test
	public void testParsingPetSimple() throws Exception {
		
		SwaggerParser swaggerParser = new SwaggerParser();
		URL url = this.getClass().getClassLoader().getResource("openapis/petstore-simple.json");
		String fileName = url.toURI().toString();
		fileName = fileName.replaceAll("file:", "");
		
		swaggerParser.parseFile(fileName);
		Map<String, Entity> entities = swaggerParser.getEntities();
		Assert.assertNotNull(entities);
		
		Entity entity = entities.get("Pet");
		Assert.assertNotNull(entity);
		Property property = entity.getProperty("id");
		Assert.assertNotNull(property);
		
		
		entity = entities.get("NewPet");
		Assert.assertNotNull(entity);
		property = entity.getProperty("name");
		Assert.assertNotNull(property);
	}
}
