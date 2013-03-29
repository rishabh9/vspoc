package me.rishabh.vspoc;

import java.io.File;
import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.reader.DataReader;
import me.rishabh.vspoc.reader.FileDataReader;
import me.rishabh.vspoc.subscribers.AverageDistanceTracker;
import me.rishabh.vspoc.subscribers.SpeedDistributionTracker;
import me.rishabh.vspoc.subscribers.VehicleCountTracker;

/**
 * The Main Class.
 * 
 * @author rishabh
 * 
 */
public class App {

    public static void main(String args[]) {

        // Create a publisher, who will publish the processed readings onto
        // different trackers (subscribers).
        ReadingsPublisher publisher = new ReadingsPublisher();

        // Subscriber who will track details regarding vehicle count
        VehicleCountTracker vct = new VehicleCountTracker();
        publisher.addObserver(vct);

        // Subscriber who will track details regarding average vehicle speed.
        SpeedDistributionTracker sdt = new SpeedDistributionTracker();
        publisher.addObserver(sdt);

        // Subscriber who will track the average distance between the vehicles.
        AverageDistanceTracker adt = new AverageDistanceTracker();
        publisher.addObserver(adt);

        // Read the input file, process the data and pass it onto the publisher.
        try {
            DataReader s = new FileDataReader(publisher, new File(args[0]));
            s.processInput();
        } catch (UnSupportedInputDataPatternException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Finally, display all of the processed data.
        vct.display();
        sdt.display();
        adt.display();
    }
}
