package fr.reference.it.referenceproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigCross implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] strings = {"POST", "PUT", "GET", "OPTIONS", "DELETE"};
        registry.addMapping("/**").allowedMethods(strings);
    }
}
