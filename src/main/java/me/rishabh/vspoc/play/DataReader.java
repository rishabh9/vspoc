package me.rishabh.vspoc.play;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import me.rishabh.vspoc.exceptions.UnImplementedInputDataPatternException;
import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.Constants;
import me.rishabh.vspoc.utilities.PropertiesHelper;
import me.rishabh.vspoc.utilities.SimpleLogger;

/**
 * 
 * @author rishabh
 *
 */
public class DataReader {

    private String inputFileName;
    private Publisher<Reading> publisher;
    private static final SimpleLogger LOG = SimpleLogger.getLogger(DataReader.class);

    /**
     * 
     * @param publisher
     *            Publisher that publishes the readings.
     */
    public DataReader(Publisher<Reading> publisher) {
        LOG.info("DataReader()", "DataReader is being initialised... added publisher");
        this.publisher = publisher;
        LOG.info("DataReader()", "Retrieving input data file name from properties file");
        inputFileName = PropertiesHelper.getProperty(Constants.INPUT_FILE_NAME, Constants.DEFAULT_INPUT_FILE_NAME);
    }

    public void readAndPublish() throws Exception {

        BufferedReader reader = null;
        try {
            // Get input data file from classpath
            URL inputFileURL = DataReader.class.getClassLoader().getResource(inputFileName);
            reader = new BufferedReader(new FileReader(inputFileURL.getFile()));

            // Initialized to last day so that the next() operation would make
            // it start from ONE.
            Day dayOfTravel = Day.FIVE;

            // Set previous time to midnight
            long previousTime = 86400000l;

            // read the first line, it becomes the current time of reading
            String line = reader.readLine();
            long currentTime = timeInMilliseconds(line);

            while (line != null) {
                Reading car = new Reading();
                if (previousTime > currentTime) {
                    dayOfTravel = dayOfTravel.next();
                }
                car.setDayOfTravel(dayOfTravel);

                if (startsWithA(line)) {
                    car.setTimeOfFrontAxleOnMarkerA(timeInMilliseconds(line));
                    line = reader.readLine();
                } else {
                    throw new UnImplementedInputDataPatternException(
                            "The input data file is probably using an unimplemented data pattern.");
                }
                if (startsWithA(line)) {
                    car.setTimeOfRearAxleOnMarkerA(timeInMilliseconds(line));
                    car.setDirection(Direction.NORTH_BOUND);
                    publisher.push(car);
                    line = reader.readLine();
                    // TODO Remove this check
                    if (!car.isValid()) {
                        throw new Exception("CAR DATA IS INVALID");
                    }

                    previousTime = currentTime;
                    currentTime = timeInMilliseconds(line);
                    continue;
                } else {
                    car.setTimeOfFrontAxleOnMarkerB(timeInMilliseconds(line));
                    line = reader.readLine();

                    if (startsWithA(line)) {
                        car.setTimeOfRearAxleOnMarkerA(timeInMilliseconds(line));
                        line = reader.readLine();
                    } else {
                        throw new UnImplementedInputDataPatternException(
                                "The input data file is probably using an unimplemented data pattern.");
                    }

                    if (startsWithB(line)) {
                        car.setTimeOfRearAxleOnMarkerB(timeInMilliseconds(line));
                        car.setDirection(Direction.SOUTH_BOUND);
                        publisher.push(car);
                        line = reader.readLine();
                        // TODO Remove this check
                        if (!car.isValid()) {
                            throw new Exception("CAR DATA IS INVALID");
                        }
                    }
                }
                previousTime = currentTime;
                currentTime = timeInMilliseconds(line);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    private long timeInMilliseconds(String line) {
        long returnValue = 0;
        try {
            if (null != line) {
                returnValue = Long.parseLong(line.substring(1));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    private boolean startsWithA(String line) {
        return line.startsWith("A");
    }

    private boolean startsWithB(String line) {
        return line.startsWith("B");
    }

    public static void main(String args[]) {
        ReadingsPublisher publisher = new ReadingsPublisher();
        DataReader s = new DataReader(publisher);
        try {
            s.readAndPublish();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        publisher.printSize();
    }
}
