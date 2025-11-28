package com.educationerp.user_management.service;

import com.educationerp.core.exception.BusinessException;
import com.educationerp.core.exception.ResourceNotFoundException;
import com.educationerp.security.dto.RegisterRequest;
import com.educationerp.security.enums.Role;
import com.educationerp.user_management.dto.CreateUserRequest;
import com.educationerp.user_management.dto.UpdateUserRequest;
import com.educationerp.user_management.dto.UserResponse;
import com.educationerp.user_management.entity.User;
import com.educationerp.user_management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for User management operations
 * Handles business logic for user CRUD operations and authentication
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user from registration form
     */
    public User registerUser(RegisterRequest request) {
        logger.info("Registering new user with email: {}", request.getEmail());

        // Validate unique email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists: " + request.getEmail());
        }

        // Generate username from email (before @ sign)
        String username = request.getEmail().substring(0, request.getEmail().indexOf("@"));
        
        // Make username unique if it already exists
        String originalUsername = username;
        int counter = 1;
        while (userRepository.existsByUsername(username)) {
            username = originalUsername + counter;
            counter++;
        }

        // Create user entity
        User user = new User();
        user.setUsername(username);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setInstitutionId(request.getInstitutionId());
        user.setBranchId(request.getBranchId());
        user.setMustChangePassword(false); // User set their own password
        
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully with username: {} (from email: {})", savedUser.getUsername(), request.getEmail());

        return savedUser;
    }

    /**
     * Create a new user
     */
    public UserResponse createUser(CreateUserRequest request) {
        logger.info("Creating new user with username: {}", request.getUsername());

        // Validate unique constraints
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists: " + request.getEmail());
        }

        // Create user entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setInstitutionId(request.getInstitutionId());
        user.setBranchId(request.getBranchId());
        user.setMustChangePassword(request.getMustChangePassword());

        User savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getId());

        return mapToUserResponse(savedUser);
    }

    /**
     * Get user by ID
     */
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return mapToUserResponse(user);
    }

    /**
     * Get user by username
     */
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return mapToUserResponse(user);
    }

    /**
     * Get all users with pagination
     */
    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::mapToUserResponse);
    }

    /**
     * Get users by role
     */
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRoleAndIsActiveTrue(role);
        return users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get users by institution
     */
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByInstitution(Long institutionId) {
        List<User> users = userRepository.findByInstitutionIdAndIsActiveTrue(institutionId);
        return users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get users by branch
     */
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByBranch(Long branchId) {
        List<User> users = userRepository.findByBranchIdAndIsActiveTrue(branchId);
        return users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Update user
     */
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        logger.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Validate unique constraints for updated values
        if (!user.getUsername().equals(request.getUsername()) && 
            userRepository.existsByUsernameAndIdNot(request.getUsername(), id)) {
            throw new BusinessException("Username already exists: " + request.getUsername());
        }

        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BusinessException("Email already exists: " + request.getEmail());
        }

        // Update user fields
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setInstitutionId(request.getInstitutionId());
        user.setBranchId(request.getBranchId());

        User updatedUser = userRepository.save(user);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());

        return mapToUserResponse(updatedUser);
    }

    /**
     * Change user password
     */
    public void changePassword(Long id, String newPassword) {
        logger.info("Changing password for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.updatePasswordChangeDate();
        userRepository.save(user);

        logger.info("Password changed successfully for user with ID: {}", id);
    }

    /**
     * Lock user account
     */
    public void lockUser(Long id) {
        logger.info("Locking user account with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.lockAccount();
        userRepository.save(user);

        logger.info("User account locked successfully with ID: {}", id);
    }

    /**
     * Unlock user account
     */
    public void unlockUser(Long id) {
        logger.info("Unlocking user account with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.unlockAccount();
        userRepository.save(user);

        logger.info("User account unlocked successfully with ID: {}", id);
    }

    /**
     * Soft delete user
     */
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        user.softDelete();
        userRepository.save(user);

        logger.info("User deleted successfully with ID: {}", id);
    }

    /**
     * Handle failed login attempt
     */
    public void handleFailedLogin(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && user.getIsActive()) {
            user.incrementFailedLoginAttempts();
            
            // Lock account after 5 failed attempts
            if (user.getFailedLoginAttempts() >= 5) {
                user.lockAccount();
                logger.warn("User account locked due to failed login attempts: {}", username);
            }
            
            userRepository.save(user);
        }
    }

    /**
     * Handle successful login
     */
    public void handleSuccessfulLogin(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && user.getIsActive()) {
            user.resetFailedLoginAttempts();
            user.updateLastLogin();
            userRepository.save(user);
        }
    }

    /**
     * Map User entity to UserResponse DTO
     */
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .isLocked(user.getIsLocked())
                .isActive(user.getIsActive())
                .lastLoginDate(user.getLastLoginDate())
                .passwordChangedDate(user.getPasswordChangedDate())
                .mustChangePassword(user.getMustChangePassword())
                .profilePictureUrl(user.getProfilePictureUrl())
                .institutionId(user.getInstitutionId())
                .branchId(user.getBranchId())
                .createdDate(user.getCreatedDate())
                .createdBy(user.getCreatedBy())
                .updatedDate(user.getUpdatedDate())
                .updatedBy(user.getUpdatedBy())
                .build();
    }
}
