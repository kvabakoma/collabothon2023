package com.tigerchamp.collabothon2023backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods(HttpMethod.DELETE.name(),
                                HttpMethod.GET.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.OPTIONS.name(),
                                HttpMethod.PATCH.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.TRACE.name())
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
