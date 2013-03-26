/**
 * 
 */
package me.rishabh.vspoc.datareader;

import java.io.FileNotFoundException;

import me.rishabh.vspoc.utilities.SimpleLogger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author rishabh
 * 
 */
public class TestFileDataReader {

	private final static SimpleLogger LOG = SimpleLogger
			.getLogger(TestFileDataReader.class);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link me.rishabh.vspoc.datareader.FileDataReader#read()} <br>
	 * Positive test - file with correct path.
	 */
	@Test
	public void testRead() {
		String methodName = "testRead()";
		String fileName = "/media/personal/Int/code/vspoc/src/main/resources/inputdata.txt";
		InputData reader = new InputData(fileName);
		try {
			LOG.info(methodName, "Reading inputdata.txt ...");
			reader.getBufferedReader();
			LOG.info(methodName, "Read inputdata.txt successfully!");
		} catch (FileNotFoundException e) {
			LOG.exception(methodName, "Couldn't load the file.", e);
			Assert.fail();
		}
	}

	/**
	 * Test method for {@link me.rishabh.vspoc.datareader.FileDataReader#read()} <br>
	 * Negative test - File with wrong path.
	 */
	@Test
	public void testReadWrongFileLocation() {
		String methodName = "testReadWrongFileLocation()";
		String filepath = "inputdata.txt";
		InputData reader = new InputData(filepath);
		try {
			LOG.info(methodName, "Reading inputdata.txt ...");
			reader.getBufferedReader();
			Assert.fail("Shouldn't be able to read the file from here - "
					+ filepath);
		} catch (Exception e) {
			LOG.info(methodName, "Couldn't find the file.");
			Assert.assertTrue(true);
		}
	}

}
