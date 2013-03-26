package me.rishabh.vspoc.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertiesHelperTest {

	@Test
	public void testGetProperty() {
		// initially not cached
		assertTrue(!PropertiesHelper.isCached());
		// loaded, cached, retrieved
		assertEquals("dummyValue", PropertiesHelper.getProperty("dummyKey"));
		// asserting cached
		assertTrue(PropertiesHelper.isCached());
	}

}
