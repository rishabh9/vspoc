/**
 * 
 */
package me.rishabh.vspoc.play;

import java.util.ArrayList;

import me.rishabh.vspoc.model.Reading;

/**
 * @author rishabh
 * @param <Reading>
 * 
 */
public class ReadingsPublisher implements Publisher<Reading> {
    
    private ArrayList<Reading> r = new ArrayList<Reading>();

    public void push(Reading t) {
        r.add(t);

    }

    public void printSize() {
        System.out.println(r.size());
        
    }

}
