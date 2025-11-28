package com.educationerp.institution_management.repository;

import com.educationerp.institution_management.entity.Institution;
import com.educationerp.security.enums.TenantType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Institution entity
 * Provides data access methods for institution management
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    /**
     * Find institution by code and active status
     */
    Optional<Institution> findByCodeAndIsActiveTrue(String code);

    /**
     * Find institution by email and active status
     */
    Optional<Institution> findByEmailAndIsActiveTrue(String email);

    /**
     * Find institution by code (including inactive)
     */
    Optional<Institution> findByCode(String code);

    /**
     * Find institution by email (including inactive)
     */
    Optional<Institution> findByEmail(String email);

    /**
     * Find institutions by tenant type
     */
    List<Institution> findByTenantTypeAndIsActiveTrue(TenantType tenantType);

    /**
     * Find institutions by tenant type with pagination
     */
    Page<Institution> findByTenantTypeAndIsActiveTrue(TenantType tenantType, Pageable pageable);

    /**
     * Find institutions by city
     */
    @Query("SELECT i FROM Institution i WHERE LOWER(i.city) = LOWER(:city) AND i.isActive = true")
    List<Institution> findByCityIgnoreCase(@Param("city") String city);

    /**
     * Find institutions by state
     */
    @Query("SELECT i FROM Institution i WHERE LOWER(i.state) = LOWER(:state) AND i.isActive = true")
    List<Institution> findByStateIgnoreCase(@Param("state") String state);

    /**
     * Find institutions by country
     */
    @Query("SELECT i FROM Institution i WHERE LOWER(i.country) = LOWER(:country) AND i.isActive = true")
    List<Institution> findByCountryIgnoreCase(@Param("country") String country);

    /**
     * Find institutions by name pattern
     */
    @Query("SELECT i FROM Institution i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%')) AND i.isActive = true")
    List<Institution> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find institutions by name pattern with pagination
     */
    @Query("SELECT i FROM Institution i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%')) AND i.isActive = true")
    Page<Institution> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Check if code exists
     */
    boolean existsByCode(String code);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if code exists excluding specific institution ID
     */
    @Query("SELECT COUNT(i) > 0 FROM Institution i WHERE i.code = :code AND i.id != :institutionId")
    boolean existsByCodeAndIdNot(@Param("code") String code, @Param("institutionId") Long institutionId);

    /**
     * Check if email exists excluding specific institution ID
     */
    @Query("SELECT COUNT(i) > 0 FROM Institution i WHERE i.email = :email AND i.id != :institutionId")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("institutionId") Long institutionId);

    /**
     * Count institutions by tenant type
     */
    long countByTenantTypeAndIsActiveTrue(TenantType tenantType);

    /**
     * Count institutions by city
     */
    @Query("SELECT COUNT(i) FROM Institution i WHERE LOWER(i.city) = LOWER(:city) AND i.isActive = true")
    long countByCityIgnoreCase(@Param("city") String city);

    /**
     * Count institutions by state
     */
    @Query("SELECT COUNT(i) FROM Institution i WHERE LOWER(i.state) = LOWER(:state) AND i.isActive = true")
    long countByStateIgnoreCase(@Param("state") String state);

    /**
     * Count institutions by country
     */
    @Query("SELECT COUNT(i) FROM Institution i WHERE LOWER(i.country) = LOWER(:country) AND i.isActive = true")
    long countByCountryIgnoreCase(@Param("country") String country);
}
