/**
 * 
 */
package me.rishabh.vspoc.publisher;

import java.util.Observable;

import me.rishabh.vspoc.model.Reading;

/**
 * 
 * @author rishabh
 * 
 */
public class ReadingsPublisher extends Observable implements Publisher<Reading> {
    
    public void push(Reading reading) {
        setChanged();
        notifyObservers(reading);
    }

}
