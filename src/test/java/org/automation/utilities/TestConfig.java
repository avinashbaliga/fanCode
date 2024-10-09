package org.automation.utilities;

import org.automation.exceptions.KeyNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private static Properties properties = null;

    public static String get(String key) {
        if (properties == null)
            initiateProperties();

        if (!properties.containsKey(key))
            throw new KeyNotFoundException(key);

        return properties.getProperty(key);
    }

    private static void initiateProperties() {
        try {
            properties = new Properties();
            File file = new File("src/main/resources/testConfig.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        }

    }
}
