package com.exadel.util;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * This will load properties from the properties file.
 *
 * @author <a href="mailto:akirilchik@exadel.com">Alexey Kirilchik</a>
 */

public class PropertyLoader {

    /* ----- CONSTANTS ----- */
    private static final String BLANK = "";
    private static final String PROP_FILE = "/application.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = BLANK;

        if (StringUtils.isNotBlank(name)) {
            value = props.getProperty(name);
        }
        return value;
    }
}
