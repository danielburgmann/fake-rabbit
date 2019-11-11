package de.db8.os.sample.fakerabbit.config;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private final Config config;
    private final String configLocation;

    public ConfigService() {
        config = new Config();
        configLocation = System.getenv("FAKE_RABBIT_CONFIG");
    }

    String getConfigMessage() {
        String configMessage;

        if(null == configLocation || configLocation.isEmpty()) {
            configMessage = "Please configure FAKE_RABBIT_CONFIG environment variable with a config location URL.";
        }
        else {
            if(config.load(configLocation)) {
                configMessage = config.getMessage();
            }
            else {
                configMessage = "Error while loading config from given location in environment variable FAKE_RABBIT_CONFIG. " + config.getInternalErrorMessage();
            }
        }

        return configMessage;
    }

    String getConfigLocation() {
        return config.getConfigLocation();
    }

}
