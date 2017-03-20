package org.stokesdrift.shores.parser;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.stokesdrift.shores.model.AppInfo;



public class HerokuAppInfoParserTest {
	
	@Test
	public void testAppInfoParse() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("examples/todos/app.json");
		String fileName = url.toURI().toString();
		fileName = fileName.replaceAll("file:", "");
		
		HerokuAppInfoParser parser = new HerokuAppInfoParser();
		parser.parseFile(fileName);
		AppInfo appInfo = parser.getAppInfo();
		Assert.assertNotNull(appInfo);
		
		Assert.assertNotNull(appInfo.getDunes());
		Assert.assertTrue(appInfo.getDunes().size() > 0);
	}

}
