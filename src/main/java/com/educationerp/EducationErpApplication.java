package com.educationerp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Education ERP System - Main Application
 * Multi-tenant education management system
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class EducationErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationErpApplication.class, args);
    }
}

