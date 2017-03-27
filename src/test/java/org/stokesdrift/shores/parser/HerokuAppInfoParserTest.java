package org.stokesdrift.shores.parser;

import org.junit.Assert;
import org.junit.Test;
import org.stokesdrift.shores.model.AppInfo;
import org.stokesdrift.shores.util.FileUtil;



public class HerokuAppInfoParserTest {
	
	@Test
	public void testAppInfoParse() throws Exception {
		String fileName = FileUtil.getFilenameFromClassfile("examples/todos/app.json");
		
		HerokuAppInfoParser parser = new HerokuAppInfoParser();
		parser.parseFile(fileName);
		AppInfo appInfo = parser.getAppInfo();
		Assert.assertNotNull(appInfo);
		
		Assert.assertNotNull(appInfo.getDunes());
		Assert.assertTrue(appInfo.getDunes().size() > 0);
	}

}
