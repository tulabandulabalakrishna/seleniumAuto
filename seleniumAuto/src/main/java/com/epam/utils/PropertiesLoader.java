package com.epam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Properties;

public class PropertiesLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Properties  configprop;

    public static void load()
    {
         Properties configprop = new Properties();
        Properties testprop = new Properties();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.APP_PROPERTIES));
            configprop.load(reader);
        }
        catch(Exception e)
        {

        }
    }

    public static String get(String key) {
       String value = configprop.getProperty(key);
        return value;
    }


}
