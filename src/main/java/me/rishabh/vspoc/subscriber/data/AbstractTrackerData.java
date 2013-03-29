package me.rishabh.vspoc.subscriber.data;

import me.rishabh.vspoc.model.Reading;

public abstract class AbstractTrackerData {
    
    protected static final double DISTANCE_BW_AXLE = 2.5; // in meters

    public AbstractTrackerData() {
        super();
    }

    public abstract void recordData(Reading data);

}