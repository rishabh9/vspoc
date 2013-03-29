/**
 * 
 */
package me.rishabh.vspoc.publisher;

import java.util.Observable;

import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

/**
 * Publisher that pushes the input readings to the various data trackers
 * (subscribers).
 * 
 * @author rishabh
 * 
 */
public class ReadingsPublisher extends Observable implements Publisher<Reading> {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(ReadingsPublisher.class);

    public void push(Reading reading) {
        LOG.debug("push()", "Entering...");
        setChanged();
        notifyObservers(reading);
    }

}
