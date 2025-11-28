package com.educationerp.core.controller;

import com.educationerp.core.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Home controller for API information and health check
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/")
@Tag(name = "Home", description = "API information and welcome endpoints")
public class HomeController {

    /**
     * Welcome endpoint - API root
     */
    @GetMapping
    @Operation(summary = "API Welcome", description = "Get API information and welcome message")
    public ResponseEntity<ApiResponse<Map<String, Object>>> welcome() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Education ERP System API");
        info.put("version", "1.0.0");
        info.put("description", "Multi-tenant Education ERP System with Spring Boot");
        info.put("javaVersion", "21");
        info.put("springBootVersion", "3.2.1");
        info.put("timestamp", LocalDateTime.now());
        info.put("documentation", "/swagger-ui.html");
        info.put("apiDocs", "/api-docs");
        info.put("health", "/actuator/health");
        
        ApiResponse<Map<String, Object>> response = ApiResponse.success(
            "Welcome to Education ERP System API", 
            info
        );
        
        return ResponseEntity.ok(response);
    }

    /**
     * API info endpoint
     */
    @GetMapping("/api")
    @Operation(summary = "API Info", description = "Get API endpoints and documentation links")
    public ResponseEntity<ApiResponse<Map<String, Object>>> apiInfo() {
        Map<String, Object> endpoints = new HashMap<>();
        
        // Auth endpoints
        Map<String, String> auth = new HashMap<>();
        auth.put("login", "POST /api/auth/login");
        auth.put("refresh", "POST /api/auth/refresh");
        auth.put("logout", "POST /api/auth/logout");
        auth.put("validate", "POST /api/auth/validate");
        endpoints.put("authentication", auth);
        
        // Public endpoints
        Map<String, String> publicEndpoints = new HashMap<>();
        publicEndpoints.put("welcome", "GET /");
        publicEndpoints.put("apiInfo", "GET /api");
        publicEndpoints.put("swagger", "GET /swagger-ui.html");
        publicEndpoints.put("apiDocs", "GET /api-docs");
        publicEndpoints.put("health", "GET /actuator/health");
        endpoints.put("public", publicEndpoints);
        
        ApiResponse<Map<String, Object>> response = ApiResponse.success(
            "API endpoints information", 
            endpoints
        );
        
        return ResponseEntity.ok(response);
    }
}
