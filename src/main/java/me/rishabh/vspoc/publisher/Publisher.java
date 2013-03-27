/**
 * 
 */
package me.rishabh.vspoc.publisher;

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
}
