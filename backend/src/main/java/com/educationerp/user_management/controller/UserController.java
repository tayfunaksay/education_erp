package com.educationerp.user_management.controller;

import com.educationerp.core.dto.ApiResponse;
import com.educationerp.security.enums.Role;
import com.educationerp.user_management.dto.CreateUserRequest;
import com.educationerp.user_management.dto.UpdateUserRequest;
import com.educationerp.user_management.dto.UserResponse;
import com.educationerp.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for User management operations
 * Provides CRUD operations for users with role-based access control
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "User management operations")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Create a new user
     */
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Create user", description = "Create a new user in the system")
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) {
        logger.info("Creating new user with username: {}", request.getUsername());
        
        UserResponse userResponse = userService.createUser(request);
        ApiResponse<UserResponse> response = ApiResponse.success("User created successfully", userResponse);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or hasRole('BRANCH_MANAGER') or @userService.canAccessUser(#id)")
    @Operation(summary = "Get user by ID", description = "Retrieve user information by ID")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        
        UserResponse userResponse = userService.getUserById(id);
        ApiResponse<UserResponse> response = ApiResponse.success("User retrieved successfully", userResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get user by username
     */
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or hasRole('BRANCH_MANAGER')")
    @Operation(summary = "Get user by username", description = "Retrieve user information by username")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(
            @Parameter(description = "Username") @PathVariable String username) {
        
        UserResponse userResponse = userService.getUserByUsername(username);
        ApiResponse<UserResponse> response = ApiResponse.success("User retrieved successfully", userResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all users with pagination
     */
    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get all users", description = "Retrieve all users with pagination")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getAllUsers(Pageable pageable) {
        Page<UserResponse> users = userService.getAllUsers(pageable);
        ApiResponse<Page<UserResponse>> response = ApiResponse.success("Users retrieved successfully", users);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get users by role
     */
    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or hasRole('BRANCH_MANAGER')")
    @Operation(summary = "Get users by role", description = "Retrieve users filtered by role")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByRole(
            @Parameter(description = "User role") @PathVariable Role role) {
        
        List<UserResponse> users = userService.getUsersByRole(role);
        ApiResponse<List<UserResponse>> response = ApiResponse.success("Users retrieved successfully", users);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get users by institution
     */
    @GetMapping("/institution/{institutionId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get users by institution", description = "Retrieve users filtered by institution")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByInstitution(
            @Parameter(description = "Institution ID") @PathVariable Long institutionId) {
        
        List<UserResponse> users = userService.getUsersByInstitution(institutionId);
        ApiResponse<List<UserResponse>> response = ApiResponse.success("Users retrieved successfully", users);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get users by branch
     */
    @GetMapping("/branch/{branchId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or hasRole('BRANCH_MANAGER')")
    @Operation(summary = "Get users by branch", description = "Retrieve users filtered by branch")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByBranch(
            @Parameter(description = "Branch ID") @PathVariable Long branchId) {
        
        List<UserResponse> users = userService.getUsersByBranch(branchId);
        ApiResponse<List<UserResponse>> response = ApiResponse.success("Users retrieved successfully", users);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Update user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or @userService.canAccessUser(#id)")
    @Operation(summary = "Update user", description = "Update user information")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        
        logger.info("Updating user with ID: {}", id);
        
        UserResponse userResponse = userService.updateUser(id, request);
        ApiResponse<UserResponse> response = ApiResponse.success("User updated successfully", userResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Change user password
     */
    @PutMapping("/{id}/password")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN') or @userService.canAccessUser(#id)")
    @Operation(summary = "Change user password", description = "Change user password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestBody String newPassword) {
        
        logger.info("Changing password for user with ID: {}", id);
        
        userService.changePassword(id, newPassword);
        ApiResponse<Void> response = ApiResponse.success("Password changed successfully", null);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Lock user account
     */
    @PutMapping("/{id}/lock")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Lock user account", description = "Lock user account")
    public ResponseEntity<ApiResponse<Void>> lockUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        
        logger.info("Locking user account with ID: {}", id);
        
        userService.lockUser(id);
        ApiResponse<Void> response = ApiResponse.success("User account locked successfully", null);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Unlock user account
     */
    @PutMapping("/{id}/unlock")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Unlock user account", description = "Unlock user account")
    public ResponseEntity<ApiResponse<Void>> unlockUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        
        logger.info("Unlocking user account with ID: {}", id);
        
        userService.unlockUser(id);
        ApiResponse<Void> response = ApiResponse.success("User account unlocked successfully", null);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Delete user (soft delete)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Delete user", description = "Soft delete user")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        
        logger.info("Deleting user with ID: {}", id);
        
        userService.deleteUser(id);
        ApiResponse<Void> response = ApiResponse.success("User deleted successfully", null);
        
        return ResponseEntity.ok(response);
    }
}
