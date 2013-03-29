package me.rishabh.vspoc.subscriber;

import me.rishabh.vspoc.model.Reading;

public abstract class AbstractTracker implements Subscriber<Reading> {
    
    public abstract void display();

}
