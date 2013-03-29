package me.rishabh.vspoc.subscribers;

import java.util.Observable;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class VehicleCountTracker extends AbstractTracker {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(VehicleCountTracker.class);

    private VehicleCountData vcData;

    public VehicleCountTracker(Observable publisher) {
        super();
        super.publisher = publisher;
        super.publisher.addObserver(this);
        vcData = new VehicleCountData();
        LOG.info("VehicleCountTracker()", "Created VehicleCountTracker ");
    }

    public void update(Observable o, Object arg) {
        if (o instanceof ReadingsPublisher) {
            Reading data = (Reading) arg;

            vcData.recordData(data, 5);
            vcData.recordData(data, 15);
            vcData.recordData(data, 20);
            vcData.recordData(data, 30);
            vcData.recordData(data, 60);
        }
    }

    /**
     * Display the vehicular count statistics.
     */
    @Override
    public void display() {
        System.out.println("\nSTATISTICS RELATED TO VEHICULAR COUNT FOR THE PAST 5 DAYS");
        for (Day day : Day.values()) {
            System.out.println("For Day " + day);
            System.out.println("\tCount of vehicles NORTH bound: "
                    + vcData.getTotalCountForDirectionDay(Direction.NORTH_BOUND, day));
            System.out.println("\tCount of vehicles SOUTH bound: "
                    + vcData.getTotalCountForDirectionDay(Direction.SOUTH_BOUND, day));
            System.out.println("\tCount of vehicles NORTH bound, in the mornings: "
                    + vcData.getTotalCountForMornings(Direction.NORTH_BOUND, day));
            System.out.println("\tCount of vehicles SOUTH bound, in the mornings: "
                    + vcData.getTotalCountForMornings(Direction.SOUTH_BOUND, day));
            System.out.println("\tCount of vehicles NORTH bound, in the evenings: "
                    + vcData.getTotalCountForEvenings(Direction.NORTH_BOUND, day));
            System.out.println("\tCount of vehicles SOUTH bound, in the evenings: "
                    + vcData.getTotalCountForEvenings(Direction.SOUTH_BOUND, day));
            System.out.println("\tThe peak volume hour for NORTH bound vehicles was: "
                    + vcData.getPeakVolumeHour(Direction.NORTH_BOUND, day));
            System.out.println("\tThe peak volume hour for SOUTH bound vehicles was: "
                    + vcData.getPeakVolumeHour(Direction.SOUTH_BOUND, day));
        }
    }
}
