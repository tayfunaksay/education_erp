package com.educationerp.course_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Course entity for the Education ERP System
 * Represents courses offered by educational institutions
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "courses", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "course_code"})
       })
public class Course extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotBlank(message = "Course code is required")
    @Size(max = 20, message = "Course code must not exceed 20 characters")
    @Column(name = "course_code", nullable = false, length = 20)
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Size(max = 200, message = "Course name must not exceed 200 characters")
    @Column(name = "course_name", nullable = false, length = 200)
    private String courseName;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 100, message = "Subject must not exceed 100 characters")
    @Column(name = "subject", length = 100)
    private String subject;

    @Size(max = 50, message = "Grade level must not exceed 50 characters")
    @Column(name = "grade_level", length = 50)
    private String gradeLevel;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "max_students")
    private Integer maxStudents;

    @Column(name = "current_students")
    private Integer currentStudents = 0;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status = CourseStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CourseType type = CourseType.REGULAR;

    @Column(name = "fee", precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(name = "is_online", nullable = false)
    private Boolean isOnline = false;

    @Size(max = 500, message = "Location must not exceed 500 characters")
    @Column(name = "location", length = 500)
    private String location;

    @Size(max = 100, message = "Instructor name must not exceed 100 characters")
    @Column(name = "instructor_name", length = 100)
    private String instructorName;

    @Column(name = "instructor_id")
    private Long instructorId;

    @Size(max = 1000, message = "Prerequisites must not exceed 1000 characters")
    @Column(name = "prerequisites", length = 1000)
    private String prerequisites;

    @Size(max = 1000, message = "Learning objectives must not exceed 1000 characters")
    @Column(name = "learning_objectives", length = 1000)
    private String learningObjectives;

    @Size(max = 1000, message = "Course materials must not exceed 1000 characters")
    @Column(name = "course_materials", length = 1000)
    private String courseMaterials;

    @Size(max = 1000, message = "Assessment methods must not exceed 1000 characters")
    @Column(name = "assessment_methods", length = 1000)
    private String assessmentMethods;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public Course() {
    }

    public Course(Long institutionId, Long branchId, String courseCode, String courseName, 
                  String description, String subject, String gradeLevel, Integer credits, 
                  Integer durationHours, Integer maxStudents, LocalDate startDate, LocalDate endDate, 
                  CourseStatus status, CourseType type, BigDecimal fee, Boolean isOnline, 
                  String location, String instructorName, Long instructorId) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.subject = subject;
        this.gradeLevel = gradeLevel;
        this.credits = credits;
        this.durationHours = durationHours;
        this.maxStudents = maxStudents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.fee = fee;
        this.isOnline = isOnline;
        this.location = location;
        this.instructorName = instructorName;
        this.instructorId = instructorId;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Integer getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(Integer currentStudents) {
        this.currentStudents = currentStudents;
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

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(String learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public String getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(String courseMaterials) {
        this.courseMaterials = courseMaterials;
    }

    public String getAssessmentMethods() {
        return assessmentMethods;
    }

    public void setAssessmentMethods(String assessmentMethods) {
        this.assessmentMethods = assessmentMethods;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isFull() {
        return maxStudents != null && currentStudents >= maxStudents;
    }

    public boolean isActive() {
        return status == CourseStatus.ACTIVE && getIsActive();
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

    public void incrementStudentCount() {
        if (currentStudents == null) {
            currentStudents = 0;
        }
        currentStudents++;
    }

    public void decrementStudentCount() {
        if (currentStudents != null && currentStudents > 0) {
            currentStudents--;
        }
    }

    public void activate() {
        this.status = CourseStatus.ACTIVE;
    }

    public void suspend() {
        this.status = CourseStatus.SUSPENDED;
    }

    public void complete() {
        this.status = CourseStatus.COMPLETED;
    }

    public void cancel() {
        this.status = CourseStatus.CANCELLED;
    }

    // Enums
    public enum CourseStatus {
        ACTIVE, SUSPENDED, COMPLETED, CANCELLED, DRAFT
    }

    public enum CourseType {
        REGULAR, INTENSIVE, WORKSHOP, SEMINAR, ONLINE, HYBRID, PRIVATE, GROUP
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Objects.equals(institutionId, course.institutionId) &&
                Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, courseCode);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", isActive=" + getIsActive() +
                '}';
    }
}
