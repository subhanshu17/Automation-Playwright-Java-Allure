package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static Properties props() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }
        return properties;
    }

    public static String get(String key) {
        return props().getProperty(key);
    }
}
