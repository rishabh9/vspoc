package me.rishabh.vspoc.reader;

import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;

/**
 * This interface provides the contract to read data from any kind if input
 * media.
 * 
 * @author rishabh
 * 
 */
public interface DataReader {

    /**
     * Read data from source and pass it on to the publisher.
     * 
     * @throws UnSupportedInputDataPatternException
     * @throws IOException
     */
    void processInput() throws UnSupportedInputDataPatternException, IOException;

}