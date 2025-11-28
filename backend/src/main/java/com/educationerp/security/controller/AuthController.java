package com.educationerp.security.controller;

import com.educationerp.core.dto.ApiResponse;
import com.educationerp.security.dto.LoginRequest;
import com.educationerp.security.dto.LoginResponse;
import com.educationerp.security.dto.RefreshTokenRequest;
import com.educationerp.security.dto.RegisterRequest;
import com.educationerp.security.jwt.JwtUtil;
import com.educationerp.security.service.CustomUserDetailsService;
import com.educationerp.user_management.entity.User;
import com.educationerp.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller for login, logout, and token management
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication and authorization endpoints")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**
     * User registration endpoint
     */
    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user and return JWT tokens")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        logger.info("Registration attempt for email: {}", registerRequest.getEmail());

        try {
            // Register user
            User user = userService.registerUser(registerRequest);
            
            // Auto-login after registration
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            CustomUserDetailsService.CustomUserPrincipal customUserPrincipal = 
                (CustomUserDetailsService.CustomUserPrincipal) userDetails;

            String tenantId = "default"; // Default tenant for now

            // Generate tokens
            String accessToken = jwtUtil.generateAccessToken(
                userDetails, tenantId, customUserPrincipal.getUser().getRole());
            String refreshToken = jwtUtil.generateRefreshToken(
                userDetails, tenantId, customUserPrincipal.getUser().getRole());

            LoginResponse loginResponse = LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(jwtUtil.extractExpiration(accessToken).getTime())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .tenantId(tenantId)
                    .build();

            ApiResponse<LoginResponse> response = ApiResponse.success("Registration successful", loginResponse);
            logger.info("Registration successful for email: {}", registerRequest.getEmail());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Registration failed for email: {}", registerRequest.getEmail(), e);
            ApiResponse<LoginResponse> response = ApiResponse.error("Registration failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * User login endpoint
     */
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT tokens")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for username: {}", loginRequest.getUsername());

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            CustomUserDetailsService.CustomUserPrincipal customUserPrincipal = 
                (CustomUserDetailsService.CustomUserPrincipal) userDetails;

            String tenantId = loginRequest.getTenantId();
            if (tenantId == null || tenantId.trim().isEmpty()) {
                tenantId = "default"; // Default tenant for now
            }

            // Generate tokens
            String accessToken = jwtUtil.generateAccessToken(
                userDetails, tenantId, customUserPrincipal.getUser().getRole());
            String refreshToken = jwtUtil.generateRefreshToken(
                userDetails, tenantId, customUserPrincipal.getUser().getRole());

            // Handle successful login
            userService.handleSuccessfulLogin(loginRequest.getUsername());

            LoginResponse loginResponse = LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(jwtUtil.extractExpiration(accessToken).getTime())
                    .username(userDetails.getUsername())
                    .role(customUserPrincipal.getUser().getRole())
                    .tenantId(tenantId)
                    .build();

            ApiResponse<LoginResponse> response = ApiResponse.success("Login successful", loginResponse);
            logger.info("Login successful for username: {}", loginRequest.getUsername());

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            // Handle failed login
            userService.handleFailedLogin(loginRequest.getUsername());
            logger.warn("Login failed for username: {}", loginRequest.getUsername());
            
            ApiResponse<LoginResponse> response = ApiResponse.error("Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Refresh token endpoint
     */
    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Generate new access token using refresh token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshRequest) {
        logger.info("Token refresh attempt");

        try {
            String refreshToken = refreshRequest.getRefreshToken();
            
            // Validate refresh token
            if (!jwtUtil.isRefreshToken(refreshToken)) {
                ApiResponse<LoginResponse> response = ApiResponse.error("Invalid refresh token");
                return ResponseEntity.badRequest().body(response);
            }

            String username = jwtUtil.extractUsername(refreshToken);
            String tenantId = jwtUtil.extractTenantId(refreshToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Generate new access token
            String newAccessToken = jwtUtil.generateAccessToken(userDetails, tenantId, jwtUtil.extractRole(refreshToken));

            LoginResponse loginResponse = LoginResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken) // Keep the same refresh token
                    .tokenType("Bearer")
                    .expiresIn(jwtUtil.extractExpiration(newAccessToken).getTime())
                    .username(username)
                    .role(jwtUtil.extractRole(refreshToken))
                    .tenantId(tenantId)
                    .build();

            ApiResponse<LoginResponse> response = ApiResponse.success("Token refreshed successfully", loginResponse);
            logger.info("Token refreshed successfully for username: {}", username);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Token refresh failed", e);
            ApiResponse<LoginResponse> response = ApiResponse.error("Token refresh failed");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Logout endpoint
     */
    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout user and invalidate tokens")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authHeader) {
        logger.info("Logout request");

        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            String username = jwtUtil.extractUsername(token);
            
            // In a real implementation, you would add the token to a blacklist
            // For now, we'll just log the logout
            logger.info("User logged out: {}", username);

            ApiResponse<Void> response = ApiResponse.success("Logout successful", null);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Logout failed", e);
            ApiResponse<Void> response = ApiResponse.error("Logout failed");
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Validate token endpoint
     */
    @PostMapping("/validate")
    @Operation(summary = "Validate token", description = "Validate JWT token")
    public ResponseEntity<ApiResponse<Boolean>> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            
            // Check if token is expired
            boolean isValid = !jwtUtil.isTokenExpired(token);
            
            ApiResponse<Boolean> response = ApiResponse.success("Token validation completed", isValid);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Token validation failed", e);
            ApiResponse<Boolean> response = ApiResponse.success("Token validation completed", false);
            return ResponseEntity.ok(response);
        }
    }
}
