package org.stokesdrift.shores.generator.freemarker;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.parser.HerokuAppInfoParser;
import org.stokesdrift.shores.util.FileUtil;

import org.junit.Assert;

import org.stokesdrift.shores.model.*;
import org.stokesdrift.shores.generator.*;

public class FreeMarkerGeneratorTest {


	private AppInfo appInfo;
	
	@Before
	public void setup() throws Exception {
		String fileName = FileUtil.getFilenameFromClassfile("examples/todos/app.json");
		HerokuAppInfoParser parser = new HerokuAppInfoParser();
		parser.parseFile(fileName);
		appInfo = parser.getAppInfo();
	}
	
	@Test
	public void testGenerator() throws Exception {
		FreemarkerGeneratorProvider provider = new FreemarkerGeneratorProvider();
		Dune dune = null;
		
		for (Dune duneToCheck: appInfo.getDunes()) {
			System.out.println("test" + duneToCheck.getName());
			 
			if("helloentity".equals(duneToCheck.getName())) {
				dune = duneToCheck;
				break;
			}
		}
		
		provider.init(appInfo, dune);
		FreeMarkerGenerator generator = (FreeMarkerGenerator)provider.getEntityGenerators().get(0);
		Entity entity = new Entity();
		entity.setName("world");
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		generator.setOutputStream(byteOut);
		generator.generateEntity(appInfo, entity);
		Assert.assertEquals("Hello world", new String(byteOut.toByteArray()));
	}
	
	
}
