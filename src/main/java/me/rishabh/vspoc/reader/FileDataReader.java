package me.rishabh.vspoc.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;
import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.publisher.Publisher;
import me.rishabh.vspoc.utilities.SimpleLogger;

/**
 * 
 * @author rishabh
 * 
 */
public class FileDataReader implements DataReader {

    private final File inputFile;
    private final Publisher<Reading> publisher;
    private static final SimpleLogger LOG = SimpleLogger.getLogger(FileDataReader.class);

    /**
     * Constructor
     * 
     * @param publisher
     *            Publisher that publishes the readings.
     * @param inputFile
     *            The input data file
     */
    public FileDataReader(Publisher<Reading> publisher, File inputFile) {
        LOG.info("DataReader()", "Initialising... publisher and file");
        this.publisher = publisher;
        this.inputFile = inputFile;
    }

    /**
     * {@inheritDoc}
     */
    public void processInput() throws UnSupportedInputDataPatternException, IOException {

        String methodName = "readAndPush()";
        BufferedReader reader = null;
        try {
            LOG.debug(methodName, "Reading input data file");
            reader = new BufferedReader(new FileReader(inputFile));

            // Initialized to last day so that the next() operation would make
            // it start from ONE.
            Day dayOfTravel = Day.FIVE;

            // Set previous time to midnight
            long previousTime = 86400000l;

            // read the first line, it becomes the current time of reading
            String line = reader.readLine();
            long currentTime = getTimeInMillisec(line);

            while (line != null) {
                Reading reading = new Reading();
                if (previousTime > currentTime) {
                    dayOfTravel = dayOfTravel.next();
                }
                reading.setDayOfTravel(dayOfTravel);

                if (startsWithA(line)) {
                    reading.setTimeOfFrontAxleOnMarkerA(getTimeInMillisec(line));
                    line = reader.readLine();
                } else {
                    throw new UnSupportedInputDataPatternException(
                            "The input data file is probably using an unsupported data pattern.");
                }
                if (startsWithA(line)) {
                    reading.setTimeOfRearAxleOnMarkerA(getTimeInMillisec(line));
                    reading.setDirection(Direction.NORTH_BOUND);
                    LOG.debug(methodName, "Sending to publisher - " + reading.toString());
                    publisher.push(reading);
                    line = reader.readLine();

                    previousTime = currentTime;
                    currentTime = getTimeInMillisec(line);
                    continue;
                } else {
                    reading.setTimeOfFrontAxleOnMarkerB(getTimeInMillisec(line));
                    line = reader.readLine();

                    if (startsWithA(line)) {
                        reading.setTimeOfRearAxleOnMarkerA(getTimeInMillisec(line));
                        line = reader.readLine();
                    } else {
                        throw new UnSupportedInputDataPatternException(
                                "The input data file is probably using an unsupported data pattern.");
                    }

                    if (startsWithB(line)) {
                        reading.setTimeOfRearAxleOnMarkerB(getTimeInMillisec(line));
                        reading.setDirection(Direction.SOUTH_BOUND);
                        LOG.debug(methodName, "Sending to publisher - " + reading.toString());
                        publisher.push(reading);
                        line = reader.readLine();
                    }
                }
                previousTime = currentTime;
                currentTime = getTimeInMillisec(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
                LOG.debug(methodName, "Finished reading file");
            }
        }
    }

    private long getTimeInMillisec(String line) throws UnSupportedInputDataPatternException {
        long returnValue = 0;
        try {
            if (null != line) {
                returnValue = Long.parseLong(line.substring(1));
            }
        } catch (NumberFormatException e) {
            LOG.exception("getTimeInMillisec()", "Invalid data format " + line, e);
            throw new UnSupportedInputDataPatternException("Invalid data format.", e);
        }
        return returnValue;
    }

    private boolean startsWithA(String line) {
        return ('A' == line.charAt(0)) ? true : false;
    }

    private boolean startsWithB(String line) {
        return ('B' == line.charAt(0)) ? true : false;
    }
}
