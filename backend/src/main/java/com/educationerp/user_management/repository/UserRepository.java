package com.educationerp.user_management.repository;

import com.educationerp.security.enums.Role;
import com.educationerp.user_management.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 * Provides data access methods for user management
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username and active status
     */
    Optional<User> findByUsernameAndIsActiveTrue(String username);

    /**
     * Find user by email and active status
     */
    Optional<User> findByEmailAndIsActiveTrue(String email);

    /**
     * Find user by username (including inactive)
     */
    Optional<User> findByUsername(String username);

    /**
     * Find user by email (including inactive)
     */
    Optional<User> findByEmail(String email);

    /**
     * Find users by role and active status
     */
    List<User> findByRoleAndIsActiveTrue(Role role);

    /**
     * Find users by role with pagination
     */
    Page<User> findByRoleAndIsActiveTrue(Role role, Pageable pageable);

    /**
     * Find users by institution ID and active status
     */
    @Query("SELECT u FROM User u WHERE u.institutionId = :institutionId AND u.isActive = true")
    List<User> findByInstitutionIdAndIsActiveTrue(@Param("institutionId") Long institutionId);

    /**
     * Find users by institution ID with pagination
     */
    @Query("SELECT u FROM User u WHERE u.institutionId = :institutionId AND u.isActive = true")
    Page<User> findByInstitutionIdAndIsActiveTrue(@Param("institutionId") Long institutionId, Pageable pageable);

    /**
     * Find users by branch ID and active status
     */
    @Query("SELECT u FROM User u WHERE u.branchId = :branchId AND u.isActive = true")
    List<User> findByBranchIdAndIsActiveTrue(@Param("branchId") Long branchId);

    /**
     * Find users by branch ID with pagination
     */
    @Query("SELECT u FROM User u WHERE u.branchId = :branchId AND u.isActive = true")
    Page<User> findByBranchIdAndIsActiveTrue(@Param("branchId") Long branchId, Pageable pageable);

    /**
     * Find locked users
     */
    List<User> findByIsLockedTrueAndIsActiveTrue();

    /**
     * Find users with failed login attempts
     */
    @Query("SELECT u FROM User u WHERE u.failedLoginAttempts > 0 AND u.isActive = true")
    List<User> findUsersWithFailedLoginAttempts();

    /**
     * Find users who must change password
     */
    List<User> findByMustChangePasswordTrueAndIsActiveTrue();

    /**
     * Find users by last login date range
     */
    @Query("SELECT u FROM User u WHERE u.lastLoginDate BETWEEN :startDate AND :endDate AND u.isActive = true")
    List<User> findByLastLoginDateBetween(@Param("startDate") LocalDateTime startDate, 
                                         @Param("endDate") LocalDateTime endDate);

    /**
     * Find users by name pattern (first name or last name)
     */
    @Query("SELECT u FROM User u WHERE (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))) AND u.isActive = true")
    List<User> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find users by name pattern with pagination
     */
    @Query("SELECT u FROM User u WHERE (LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))) AND u.isActive = true")
    Page<User> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if username exists excluding specific user ID
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.id != :userId")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("userId") Long userId);

    /**
     * Check if email exists excluding specific user ID
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.id != :userId")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("userId") Long userId);

    /**
     * Count users by role
     */
    long countByRoleAndIsActiveTrue(Role role);

    /**
     * Count users by institution
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.institutionId = :institutionId AND u.isActive = true")
    long countByInstitutionIdAndIsActiveTrue(@Param("institutionId") Long institutionId);

    /**
     * Count users by branch
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.branchId = :branchId AND u.isActive = true")
    long countByBranchIdAndIsActiveTrue(@Param("branchId") Long branchId);
}
