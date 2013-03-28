package me.rishabh.vspoc.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A wrapper around the {@link java.util.logging.Logger} to make logging simpler
 * to write and read.
 * 
 * @author rishabh
 */
public class SimpleLogger {

    private static Logger LOG;
    private Class<?> logClass;

    private SimpleLogger(Class<?> logClass) {
        this.logClass = logClass;
    }

    /**
     * Method to create a Logger for specified class.
     * 
     * @param logForClass
     *            Class for which logging is required.
     * @return An instance of SimpleLogger
     */
    public static SimpleLogger getLogger(Class<?> logForClass) {
        LOG = java.util.logging.Logger.getLogger(logForClass.getName());
        return new SimpleLogger(logForClass);
    }

    /**
     * Log a message with Level - INFO
     * 
     * @param methodName
     *            Name of method containing the log.
     * @param message
     *            Message to be logged
     */
    public void info(String methodName, String message) {
        LOG.logp(Level.INFO, logClass.getName(), methodName, message);
    }

    /**
     * Log a message with Level - SEVERE
     * 
     * @param methodName
     *            Name of method containing the log.
     * @param message
     *            Message to be logged
     */
    public void error(String methodName, String message) {
        LOG.logp(Level.SEVERE, logClass.getName(), methodName, message);
    }

    /**
     * Log a message with Level - FINEST
     * 
     * @param methodName
     *            Name of method containing the log.
     * @param message
     *            Message to be logged
     */
    public void debug(String methodName, String message) {
            LOG.logp(Level.FINEST, logClass.getName(), methodName, message);
    }

    /**
     * Log an exception with Level - SEVERE
     * 
     * @param methodName
     *            Name of method containing the log.
     * @param message
     *            Message to be logged
     * @param exception
     *            The exception that needs to be logged.
     */
    public void exception(String methodName, String message, Throwable exception) {
        LOG.logp(Level.SEVERE, logClass.getName(), methodName, message, exception);
    }

    /**
     * 
     * @return TRUE if debug is enabled.
     */
    public boolean isDebugEnabled() {
        return LOG.isLoggable(Level.FINEST);
    }

}
