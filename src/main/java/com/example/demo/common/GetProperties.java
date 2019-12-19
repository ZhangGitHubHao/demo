package com.example.demo.common;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author 860118060
 */
public final class GetProperties {
    public static final String EXCEPTION = "exception";

    /** 读取配置文件 */
    private static final String[] RESOURCENAMES = new String[] { "exception" };

    private static Properties properties;


    public static String getProperty(final String key) {

        return getProperties().getProperty(key);
    }


    public static String getProperty(final String key, final String defaultValue) {

        return getProperties().getProperty(key, defaultValue);
    }


    public static Enumeration<?> propertyNames() {

        return getProperties().propertyNames();
    }


    public static synchronized boolean contains(final Object value) {

        return getProperties().contains(value);
    }


    public static synchronized boolean containsKey(final Object key) {

        return getProperties().containsKey(key);
    }


    public static boolean containsValue(final Object value) {

        return getProperties().containsValue(value);
    }


    public static synchronized Enumeration<Object> elements() {

        return getProperties().elements();
    }


    public static Set<Map.Entry<Object, Object>> entrySet() {

        return getProperties().entrySet();
    }


    public static synchronized Object get(final Object key) {

        return getProperties().get(key);
    }


    public static synchronized Enumeration<Object> keys() {

        return getProperties().keys();
    }


    public static Set<Object> keySet() {

        return getProperties().keySet();
    }


    public static synchronized int size() {

        return getProperties().size();
    }


    @Override
    public synchronized String toString() {

        return getProperties().toString();
    }

    private static Properties getProperties() {

        if (properties == null) {
            properties = new Properties();
            for (String bundleName : RESOURCENAMES) {
                PropertyResourceBundle bundle;
                try {
                    bundle = (PropertyResourceBundle) ResourceBundle.getBundle(bundleName, Locale.SIMPLIFIED_CHINESE);
                } catch (MissingResourceException e) {
                    continue;
                }
                Enumeration<String> keys = bundle.getKeys();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    String keyName = bundleName.substring(bundleName.lastIndexOf(".") + 1);

                    properties.setProperty(keyName + "." + key, bundle.getString(key));

                }
            }
        }
        return properties;
    }
}
