package me.rishabh.vspoc.utilities;

import java.io.IOException;
import java.util.Properties;

/**
 * Class to load and cache the properties file
 * 
 * @author rishabh
 * 
 */
public final class PropertiesHelper {

    private static final Properties PROP = new Properties();
    private static boolean cached;

    private static final SimpleLogger LOG = SimpleLogger.getLogger(PropertiesHelper.class);

    private PropertiesHelper() throws IOException {
    }

    private static void loadPropertiesFile() {
        String methodName = "loadPropertiesFile()";
        LOG.info(methodName, "Reading properties file from classpath...");
        try {
            PROP.load(PropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties"));
            cached = true;
            LOG.info(methodName, "Properties file loaded and cached");
        } catch (IOException e) {
            LOG.exception(methodName, "Properties file not found in classpath", e);
        }
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns null
     * if the property is not found.
     * 
     * @param key
     *            the property key.
     * @return the value in this property list with the specified key value.
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns the
     * default value argument if the property is not found.
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            a default value.
     * @return the value in this property list with the specified key value.
     */
    public static String getProperty(String key, String defaultValue) {
        String methodName = "getProperty(key,defaultValue)";

        LOG.debug("getProperty()", "getting value for key - " + key);
        if (!cached) {
            LOG.info(methodName, "Properties file wasn't loaded! loading...");
            loadPropertiesFile();
        }
        return PROP.getProperty(key, defaultValue);
    }

    /**
     * Method to help in unit testing
     * 
     * @return TRUE if properties file is cached.
     */
    static boolean isCached() {
        return cached;
    }

}
