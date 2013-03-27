package me.rishabh.vspoc.reader;

import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;

public interface DataReader {

    /**
     * Read data from source and pass it on to the publisher.
     * 
     * @throws UnSupportedInputDataPatternException
     * @throws IOException
     */
    public abstract void readAndPush() throws UnSupportedInputDataPatternException, IOException;

}