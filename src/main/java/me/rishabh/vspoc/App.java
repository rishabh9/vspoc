package me.rishabh.vspoc;

import java.io.File;
import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.reader.DataReader;
import me.rishabh.vspoc.reader.FileDataReader;
import me.rishabh.vspoc.subscriber.AverageDistanceTracker;
import me.rishabh.vspoc.subscriber.SpeedDistributionTracker;
import me.rishabh.vspoc.subscriber.VehicleCountTracker;
import me.rishabh.vspoc.utilities.SimpleLogger;

/**
 * The Main Class.
 * 
 * @author rishabh
 * 
 */
public class App {
    
    private static final SimpleLogger LOG = SimpleLogger.getLogger(App.class);

    public static void main(String args[]) {
        
        String methodName = "main([])";

        // Create a publisher, who will publish the processed readings onto
        // different trackers (subscribers).
        ReadingsPublisher publisher = new ReadingsPublisher();
        LOG.debug(methodName, "Created publisher");

        // Subscriber who will track details regarding vehicle count
        VehicleCountTracker vct = new VehicleCountTracker();
        publisher.addObserver(vct);
        LOG.debug(methodName, "Added Vehicle count tracker");

        // Subscriber who will track details regarding average vehicle speed.
        SpeedDistributionTracker sdt = new SpeedDistributionTracker();
        publisher.addObserver(sdt);
        LOG.debug(methodName, "Added Speed distribution tracker");

        // Subscriber who will track the average distance between the vehicles.
        AverageDistanceTracker adt = new AverageDistanceTracker();
        publisher.addObserver(adt);
        LOG.debug(methodName, "Added Average distance tracker");

        // Read the input file, process the data and pass it onto the publisher.
        try {
            LOG.debug(methodName, "Accessing the specified file");
            DataReader s = new FileDataReader(publisher, new File(args[0]));
            LOG.debug(methodName, "Starting to process the file...");
            s.processInput();
            LOG.debug(methodName, "Processed the file and trackers updated");
            
            // Finally, display all of the processed data.
            LOG.debug(methodName, "Now displaying the data from the trackers");
            vct.display();
            sdt.display();
            adt.display();
        } catch (UnSupportedInputDataPatternException e) {
            LOG.exception(methodName, "The input file is either invalid or not supported.", e);
        } catch (IOException e) {
            LOG.exception(methodName, "Error reading the input file.", e);
        }

    }
}
