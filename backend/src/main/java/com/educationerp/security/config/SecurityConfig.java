package com.educationerp.security.config;

import com.educationerp.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Security configuration for the Education ERP System
 * Configures JWT authentication, CORS, and security policies
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                // Public endpoints
                .requestMatchers("/").permitAll()
                .requestMatchers("/api").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/api-docs/**").permitAll()
                .requestMatchers("/api-docs").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                
                // Admin endpoints
                .requestMatchers("/api/admin/**").hasRole("SUPER_ADMIN")
                
                // Institution admin endpoints
                .requestMatchers("/api/institution/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN")
                
                // Branch manager endpoints
                .requestMatchers("/api/branch/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER")
                
                // Course manager endpoints
                .requestMatchers("/api/course/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER", "COURSE_MANAGER")
                
                // Teacher endpoints
                .requestMatchers("/api/teacher/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER", "TEACHER")
                
                // Student endpoints
                .requestMatchers("/api/student/**").hasAnyRole("STUDENT", "PARENT", "SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER", "TEACHER")
                
                // Parent endpoints
                .requestMatchers("/api/parent/**").hasAnyRole("PARENT", "SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER")
                
                // Payment endpoints
                .requestMatchers("/api/payment/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER", "ACCOUNTANT")
                
                // Report endpoints
                .requestMatchers("/api/reports/**").hasAnyRole("SUPER_ADMIN", "INSTITUTION_ADMIN", "BRANCH_MANAGER", "REPORT_VIEWER", "ACCOUNTANT")
                
                // All other requests need authentication
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
