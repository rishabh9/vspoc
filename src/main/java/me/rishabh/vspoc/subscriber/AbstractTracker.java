package me.rishabh.vspoc.subscriber;

import me.rishabh.vspoc.model.Reading;

/**
 * 
 * @author rishabh
 * 
 */
public abstract class AbstractTracker implements Subscriber<Reading> {

    /**
     * Display the statistics.
     */
    public abstract void display();

}
