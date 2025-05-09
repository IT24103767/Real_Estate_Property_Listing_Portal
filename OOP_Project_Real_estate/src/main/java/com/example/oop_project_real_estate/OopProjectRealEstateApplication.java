package com.example.oop_project_real_estate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OopProjectRealEstateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OopProjectRealEstateApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OopProjectRealEstateApplication.class);
    }
}