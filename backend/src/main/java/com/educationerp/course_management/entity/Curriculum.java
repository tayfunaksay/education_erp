package com.educationerp.course_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Curriculum entity for the Education ERP System
 * Represents curriculum/program structures
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "curricula", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "curriculum_code"})
       })
public class Curriculum extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotBlank(message = "Curriculum code is required")
    @Size(max = 20, message = "Curriculum code must not exceed 20 characters")
    @Column(name = "curriculum_code", nullable = false, length = 20)
    private String curriculumCode;

    @NotBlank(message = "Curriculum name is required")
    @Size(max = 200, message = "Curriculum name must not exceed 200 characters")
    @Column(name = "curriculum_name", nullable = false, length = 200)
    private String curriculumName;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 100, message = "Department must not exceed 100 characters")
    @Column(name = "department", length = 100)
    private String department;

    @Size(max = 50, message = "Degree level must not exceed 50 characters")
    @Column(name = "degree_level", length = 50)
    private String degreeLevel;

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "duration_years")
    private Integer durationYears;

    @Column(name = "duration_semesters")
    private Integer durationSemesters;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CurriculumStatus status = CurriculumStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CurriculumType type = CurriculumType.UNDERGRADUATE;

    @Size(max = 100, message = "Coordinator name must not exceed 100 characters")
    @Column(name = "coordinator_name", length = 100)
    private String coordinatorName;

    @Column(name = "coordinator_id")
    private Long coordinatorId;

    @Size(max = 1000, message = "Learning outcomes must not exceed 1000 characters")
    @Column(name = "learning_outcomes", length = 1000)
    private String learningOutcomes;

    @Size(max = 1000, message = "Career prospects must not exceed 1000 characters")
    @Column(name = "career_prospects", length = 1000)
    private String careerProspects;

    @Size(max = 1000, message = "Admission requirements must not exceed 1000 characters")
    @Column(name = "admission_requirements", length = 1000)
    private String admissionRequirements;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public Curriculum() {
    }

    public Curriculum(Long institutionId, Long branchId, String curriculumCode, String curriculumName, 
                     String description, String department, String degreeLevel, Integer totalCredits, 
                     Integer durationYears, Integer durationSemesters, LocalDate startDate, LocalDate endDate, 
                     CurriculumStatus status, CurriculumType type, String coordinatorName, Long coordinatorId) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.curriculumCode = curriculumCode;
        this.curriculumName = curriculumName;
        this.description = description;
        this.department = department;
        this.degreeLevel = degreeLevel;
        this.totalCredits = totalCredits;
        this.durationYears = durationYears;
        this.durationSemesters = durationSemesters;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.coordinatorName = coordinatorName;
        this.coordinatorId = coordinatorId;
    }

    // Getters and Setters
    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCurriculumCode() {
        return curriculumCode;
    }

    public void setCurriculumCode(String curriculumCode) {
        this.curriculumCode = curriculumCode;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Integer getDurationYears() {
        return durationYears;
    }

    public void setDurationYears(Integer durationYears) {
        this.durationYears = durationYears;
    }

    public Integer getDurationSemesters() {
        return durationSemesters;
    }

    public void setDurationSemesters(Integer durationSemesters) {
        this.durationSemesters = durationSemesters;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CurriculumStatus getStatus() {
        return status;
    }

    public void setStatus(CurriculumStatus status) {
        this.status = status;
    }

    public CurriculumType getType() {
        return type;
    }

    public void setType(CurriculumType type) {
        this.type = type;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(String learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }

    public String getCareerProspects() {
        return careerProspects;
    }

    public void setCareerProspects(String careerProspects) {
        this.careerProspects = careerProspects;
    }

    public String getAdmissionRequirements() {
        return admissionRequirements;
    }

    public void setAdmissionRequirements(String admissionRequirements) {
        this.admissionRequirements = admissionRequirements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isActive() {
        return status == CurriculumStatus.ACTIVE && getIsActive();
    }

    public boolean isOngoing() {
        LocalDate today = LocalDate.now();
        return startDate != null && startDate.isBefore(today) && 
               (endDate == null || endDate.isAfter(today));
    }

    public boolean isUpcoming() {
        return startDate != null && startDate.isAfter(LocalDate.now());
    }

    public boolean isCompleted() {
        return endDate != null && endDate.isBefore(LocalDate.now());
    }

    public void activate() {
        this.status = CurriculumStatus.ACTIVE;
    }

    public void suspend() {
        this.status = CurriculumStatus.SUSPENDED;
    }

    public void complete() {
        this.status = CurriculumStatus.COMPLETED;
    }

    public void archive() {
        this.status = CurriculumStatus.ARCHIVED;
    }

    // Enums
    public enum CurriculumStatus {
        ACTIVE, SUSPENDED, COMPLETED, ARCHIVED, DRAFT
    }

    public enum CurriculumType {
        UNDERGRADUATE, GRADUATE, POSTGRADUATE, CERTIFICATE, DIPLOMA, ASSOCIATE, BACHELOR, MASTER, DOCTORATE
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Curriculum that = (Curriculum) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(curriculumCode, that.curriculumCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, curriculumCode);
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", curriculumCode='" + curriculumCode + '\'' +
                ", curriculumName='" + curriculumName + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", isActive=" + getIsActive() +
                '}';
    }
}
