package me.rishabh.vspoc.reader;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;
import me.rishabh.vspoc.publisher.ReadingsPublisher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDataReaderTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProcessInputValidFile() {
        ReadingsPublisher publisher = new ReadingsPublisher();
        // SpeedDistributionTracker sdt = new SpeedDistributionTracker();
        // publisher.addObserver(sdt);
        URL filename = FileDataReaderTest.class.getClassLoader().getResource("sample.txt");
        DataReader dataReader = new FileDataReader(publisher, new File(filename.getFile()));
        try {
            dataReader.processInput();
        } catch (UnSupportedInputDataPatternException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
