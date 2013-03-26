package me.rishabh.vspoc.datareader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Interface to read the input data
 * 
 * @author rishabh
 */
public class InputData {

	private File file;

	public InputData(String pathToFile) {
		file = new File(pathToFile);
	}

	/**
	 * Get Reader to read the input data
	 * 
	 * @return Returns instance of {@link java.io.BufferedReader}
	 */
	public BufferedReader getBufferedReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}
}
