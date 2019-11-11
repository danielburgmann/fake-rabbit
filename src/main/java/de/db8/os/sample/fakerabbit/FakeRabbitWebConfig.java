package de.db8.os.sample.fakerabbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure the web app
 *
 * For CORS example see:
 * https://www.baeldung.com/spring-cors
 */

@Configuration
public class FakeRabbitWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
