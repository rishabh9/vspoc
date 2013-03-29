package me.rishabh.vspoc.subscribers;

import java.util.Observable;

import me.rishabh.vspoc.model.Reading;

public abstract class AbstractTracker implements Subscriber<Reading> {
    
    protected Observable publisher;

    public abstract void display();

}
