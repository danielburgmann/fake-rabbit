package de.db8.os.sample.fakerabbit.openfeign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Configuration to fine tune feign behaviour.
 */
public class FeignConfiguration {

    /**
     * See: https://cloud.spring.io/spring-cloud-static/spring-cloud-openfeign/2.2.2.RELEASE/reference/html/#feign-logging
     * OpenFeign client interfaces have to be explicitly configured with "configuration = FeignConfiguration.class".
     * Will only come to action when OpenFeign client log level is set to DEBUG.
     * @return OpenFeign log level when DEBUG is enabled
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
