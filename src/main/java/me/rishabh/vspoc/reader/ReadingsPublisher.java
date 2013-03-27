/**
 * 
 */
package me.rishabh.vspoc.reader;

import java.util.ArrayList;

import me.rishabh.vspoc.model.Reading;

/**
 * @author rishabh
 * @param <T>
 * @param <Reading>
 * 
 */
public class ReadingsPublisher implements Publisher<Reading> {

    private ArrayList<Reading> r = new ArrayList<Reading>();

    public void push(Reading reading) {
        // TODO: Write code to push to subscribers.
        r.add(reading);
    }

    /*
     * TODO: Remove this method
     */
    public void printSize() {
        System.out.println(r.size());

    }
}
