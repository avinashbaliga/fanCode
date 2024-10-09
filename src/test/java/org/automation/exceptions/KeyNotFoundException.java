package org.automation.exceptions;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String key) {
        super("Key '" + key + "' not found in testConfig.properties file");
    }
}
