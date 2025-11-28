package com.educationerp.institution_management.repository;

import com.educationerp.institution_management.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Branch entity
 * Provides data access methods for branch management
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    /**
     * Find branch by institution ID and code
     */
    Optional<Branch> findByInstitutionIdAndCodeAndIsActiveTrue(Long institutionId, String code);

    /**
     * Find branches by institution ID
     */
    List<Branch> findByInstitutionIdAndIsActiveTrue(Long institutionId);

    /**
     * Find branches by institution ID with pagination
     */
    Page<Branch> findByInstitutionIdAndIsActiveTrue(Long institutionId, Pageable pageable);

    /**
     * Find branch by code within institution
     */
    @Query("SELECT b FROM Branch b WHERE b.institutionId = :institutionId AND b.code = :code")
    Optional<Branch> findByInstitutionIdAndCode(@Param("institutionId") Long institutionId, @Param("code") String code);

    /**
     * Find branches by city
     */
    @Query("SELECT b FROM Branch b WHERE LOWER(b.city) = LOWER(:city) AND b.isActive = true")
    List<Branch> findByCityIgnoreCase(@Param("city") String city);

    /**
     * Find branches by state
     */
    @Query("SELECT b FROM Branch b WHERE LOWER(b.state) = LOWER(:state) AND b.isActive = true")
    List<Branch> findByStateIgnoreCase(@Param("state") String state);

    /**
     * Find branches by country
     */
    @Query("SELECT b FROM Branch b WHERE LOWER(b.country) = LOWER(:country) AND b.isActive = true")
    List<Branch> findByCountryIgnoreCase(@Param("country") String country);

    /**
     * Find branches by name pattern
     */
    @Query("SELECT b FROM Branch b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) AND b.isActive = true")
    List<Branch> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find branches by name pattern with pagination
     */
    @Query("SELECT b FROM Branch b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) AND b.isActive = true")
    Page<Branch> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Find branches by institution and city
     */
    @Query("SELECT b FROM Branch b WHERE b.institutionId = :institutionId AND LOWER(b.city) = LOWER(:city) AND b.isActive = true")
    List<Branch> findByInstitutionIdAndCityIgnoreCase(@Param("institutionId") Long institutionId, @Param("city") String city);

    /**
     * Find branches by institution and state
     */
    @Query("SELECT b FROM Branch b WHERE b.institutionId = :institutionId AND LOWER(b.state) = LOWER(:state) AND b.isActive = true")
    List<Branch> findByInstitutionIdAndStateIgnoreCase(@Param("institutionId") Long institutionId, @Param("state") String state);

    /**
     * Check if code exists within institution
     */
    @Query("SELECT COUNT(b) > 0 FROM Branch b WHERE b.institutionId = :institutionId AND b.code = :code")
    boolean existsByInstitutionIdAndCode(@Param("institutionId") Long institutionId, @Param("code") String code);

    /**
     * Check if code exists within institution excluding specific branch ID
     */
    @Query("SELECT COUNT(b) > 0 FROM Branch b WHERE b.institutionId = :institutionId AND b.code = :code AND b.id != :branchId")
    boolean existsByInstitutionIdAndCodeAndIdNot(@Param("institutionId") Long institutionId, @Param("code") String code, @Param("branchId") Long branchId);

    /**
     * Count branches by institution
     */
    long countByInstitutionIdAndIsActiveTrue(Long institutionId);

    /**
     * Count branches by city
     */
    @Query("SELECT COUNT(b) FROM Branch b WHERE LOWER(b.city) = LOWER(:city) AND b.isActive = true")
    long countByCityIgnoreCase(@Param("city") String city);

    /**
     * Count branches by state
     */
    @Query("SELECT COUNT(b) FROM Branch b WHERE LOWER(b.state) = LOWER(:state) AND b.isActive = true")
    long countByStateIgnoreCase(@Param("state") String state);

    /**
     * Count branches by country
     */
    @Query("SELECT COUNT(b) FROM Branch b WHERE LOWER(b.country) = LOWER(:country) AND b.isActive = true")
    long countByCountryIgnoreCase(@Param("country") String country);
}
