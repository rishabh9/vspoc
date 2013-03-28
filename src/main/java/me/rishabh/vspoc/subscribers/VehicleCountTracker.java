package me.rishabh.vspoc.subscribers;

import java.util.Observable;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class VehicleCountTracker implements Subscriber<Reading> {

    public Observable publisher;

    private static final SimpleLogger LOG = SimpleLogger.getLogger(VehicleCountTracker.class);
    
    private VehicleCountTrackerData trackerData;

    public VehicleCountTracker(Observable publisher) {
        super();
        this.publisher = publisher;
        this.publisher.addObserver(this);
        trackerData = new VehicleCountTrackerData();
        LOG.info("VehicleCountTracker()", "Created VehicleCountTracker ");
    }

    public void update(Observable o, Object arg) {
        if (o instanceof ReadingsPublisher) {
            Reading data = (Reading) arg;
            
            trackerData.recordData(data,5);
            trackerData.recordData(data,15);
            trackerData.recordData(data,20);
            trackerData.recordData(data,30);
            trackerData.recordData(data,60);
        }
    }

    /**
     * Display the vehicular count statistics.
     */
    public void display() {
        System.out.println("DISPLAYING STATISTICS RELATED TO VEHICULAR COUNT FOR THE PAST 5 DAYS\n");
        System.out.println("On an average, the count of vehicles travelling north bound, for every 15 minutes was:");
        System.out.println(getSessionsAvgCountForEvery15minutes(Direction.NORTH_BOUND));
    }

    private long getSessionsAvgCountForEvery15minutes(Direction direction) {
        // TODO Auto-generated method stub
        return 0l;
    }
}
