/**
 * 
 */
package me.rishabh.vspoc.reader;

/**
 * @author rishabh
 * 
 */
public interface Publisher<T> {

    /**
     * Push the data to the subscribers.
     * 
     * @param t
     *            Object that needs to be pushed to the subscribers.
     */
    void push(T t);
    
    void printSize();
}
