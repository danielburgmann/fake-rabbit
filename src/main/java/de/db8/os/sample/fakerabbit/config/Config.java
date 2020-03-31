package de.db8.os.sample.fakerabbit.config;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

/**
 * A generic configuration providing object. It loads configuration failsafe from a location. Currently URLs and
 * file paths are supported as locations
 */

class Config {

    private static final String UTF_8 = StandardCharsets.UTF_8.toString();

    private String configLocation;

    private String configContent;
    private String internalErrorMessage;
    private Properties configProps;

    boolean load(String configLocation) {
        internalErrorMessage = "";
        this.configLocation = configLocation;

        return loadConfigContent()
            && buildProperties();
    }

    String getMessage() {
        return configProps.getProperty("message");
    }

    String getConfigLocation() {
        return configLocation;
    }

    String getInternalErrorMessage() {
        return internalErrorMessage;
    }

    private boolean loadConfigContent() {
        try {
            URL sourceUrl = new URL(configLocation);
            return loadFromUrl(sourceUrl);
        }
        catch (MalformedURLException e) {
            File configFile = new File(configLocation);
            if(configFile.exists() && configFile.isFile() && configFile.canRead()) {
                return loadFromFile(configFile);
            }
            else {
                internalErrorMessage = "Config location is a malformed URL.";
                return false;
            }
        }
    }

    private boolean loadFromUrl(URL sourceUrl) {
        try {
            InputStream source = sourceUrl.openStream();
            return readFromStream(source);
        }
        catch (IOException e) {
            internalErrorMessage = "I/O error while reading from config location as URL.";
            return false;
        }
    }

    private boolean loadFromFile(File configFile) {
        try {
            InputStream source = new FileInputStream(configFile);
            return readFromStream(source);
        }
        catch (IOException ioe) {
            internalErrorMessage = "I/O error while reading from config location as local file.";
            return false;
        }
    }

    // inspired by
    // https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code?answertab=votes#tab-top
    @SuppressWarnings("SameReturnValue")
    private boolean readFromStream(InputStream source) {
        Scanner scanner = new Scanner(source, UTF_8);
        scanner.useDelimiter("\\A");
        configContent = scanner.hasNext() ? scanner.next() : "";
        return true;
    }

    private boolean buildProperties() {
        StringReader reader = new StringReader(configContent);

        configProps = new Properties();
        try {
            configProps.load(reader);
            return true;
        }
        catch (IOException e) {
            internalErrorMessage = "I/O error while loading properties: " + e.getMessage();
            return false;
        }
    }

}
