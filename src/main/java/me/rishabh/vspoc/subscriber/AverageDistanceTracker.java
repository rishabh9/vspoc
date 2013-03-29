/**
 * 
 */
package me.rishabh.vspoc.subscriber;

import java.util.Observable;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.subscriber.data.AverageDistanceData;
import me.rishabh.vspoc.utilities.SimpleLogger;

/**
 * @author rishabh
 * 
 */
public class AverageDistanceTracker extends AbstractTracker {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(AverageDistanceTracker.class);
    private AverageDistanceData adtData;

    public AverageDistanceTracker() {
        super();
        adtData = new AverageDistanceData();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof ReadingsPublisher) {
            Reading data = (Reading) arg;
            LOG.debug("update()", "Recording data: " + data.toString());
            adtData.recordData(data);
        }
    }

    /**
     * 
     */
    @Override
    public void display() {
        LOG.info("display()", "Displaying statistics related to avg. distance between vehicles.");
        System.out.println("\nSTATISTICS OF AVERAGE DISTANCE BETWEEN VEHICLES FOR THE PAST 5 DAYS");
        for (Day day : Day.values()) {
            System.out.println("For Day " + day);
            for (int i = 1; i <= 24; i++) {
                System.out.println("\tAverage distance between vehicles NORTH bound in the " + i + " hour: "
                        + adtData.getAverageDistance(Direction.NORTH_BOUND, day, i) + " meters");
                System.out.println("\tAverage distance between vehicles SOUTH bound in the " + i + " hour: "
                        + adtData.getAverageDistance(Direction.SOUTH_BOUND, day, i) + " meters");
            }
        }

    }

}
