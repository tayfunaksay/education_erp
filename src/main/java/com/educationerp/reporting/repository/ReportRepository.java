package com.educationerp.reporting.repository;

import com.educationerp.reporting.entity.Report;
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
 * Repository for Report entity
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * Find reports by institution ID
     */
    List<Report> findByInstitutionId(Long institutionId);

    /**
     * Find reports by type
     */
    List<Report> findByReportType(String reportType);

    /**
     * Find reports by status
     */
    List<Report> findByStatus(String status);

    /**
     * Find reports by institution ID and status
     */
    List<Report> findByInstitutionIdAndStatus(Long institutionId, String status);

    /**
     * Find reports by date range
     */
    @Query("SELECT r FROM Report r WHERE r.createdDate BETWEEN :startDate AND :endDate")
    List<Report> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                  @Param("endDate") LocalDateTime endDate);

    /**
     * Find all reports by institution ID with pagination
     */
    Page<Report> findByInstitutionId(Long institutionId, Pageable pageable);

    /**
     * Find reports by user ID who generated them
     */
    List<Report> findByCreatedBy(String username);

    /**
     * Check if report exists with given identifier
     */
    boolean existsByReportIdentifier(String reportIdentifier);

    /**
     * Find report by identifier
     */
    Optional<Report> findByReportIdentifier(String reportIdentifier);

    /**
     * Find active reports by institution ID ordered by created date descending
     */
    List<Report> findByInstitutionIdAndIsActiveTrueOrderByCreatedDateDesc(Long institutionId);

    /**
     * Find active reports by institution ID and category ordered by created date descending
     */
    List<Report> findByInstitutionIdAndCategoryAndIsActiveTrueOrderByCreatedDateDesc(Long institutionId, Report.ReportCategory category);

    /**
     * Find scheduled active reports by status
     */
    List<Report> findByIsScheduledTrueAndStatusAndIsActiveTrue(Report.ReportStatus status);
}

