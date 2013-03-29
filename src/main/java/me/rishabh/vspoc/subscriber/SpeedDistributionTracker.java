package me.rishabh.vspoc.subscriber;

import java.util.Observable;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.subscriber.data.SpeedDistributionData;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class SpeedDistributionTracker extends AbstractTracker {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(SpeedDistributionTracker.class);
    private SpeedDistributionData spdData;

    public SpeedDistributionTracker() {
        super();
        spdData = new SpeedDistributionData();
        LOG.info("SpeedDistributionTracker()", "Created VehicleCountTracker ");
    }

    public void update(Observable o, Object arg) {
        if (o instanceof ReadingsPublisher) {
            Reading data = (Reading) arg;
            spdData.recordData(data);
        }
    }

    @Override
    public void display() {
        System.out.println("\nSTATISTICS RELATED TO VEHICULAR SPEED FOR THE PAST 5 DAYS");
        for (Day day : Day.values()) {
            System.out.println("For Day " + day);
            for (int i = 1; i <= 24; i++) {
                System.out.println("\tAverage speed of vehicles NORTH bound in the " + i + " hour: "
                        + spdData.getAverageSpeed(Direction.NORTH_BOUND, day, i) + " Kmph");
                System.out.println("\tAverage speed of vehicles SOUTH bound in the " + i + " hour: "
                        + spdData.getAverageSpeed(Direction.SOUTH_BOUND, day, i) + " Kmph");
            }
        }
    }

}
