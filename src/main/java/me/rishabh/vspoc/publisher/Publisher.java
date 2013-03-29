/**
 * 
 */
package me.rishabh.vspoc.publisher;

/**
 * Classes implementing this interface are recognised as publishers.
 * 
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
