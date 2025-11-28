package com.educationerp.student_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Enrollment entity for the Education ERP System
 * Represents student enrollments in courses
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "enrollments", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"student_id", "course_id"})
       })
public class Enrollment extends BaseEntity {

    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull(message = "Course ID is required")
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @NotNull(message = "Enrollment date is required")
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;

    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public Enrollment() {
    }

    public Enrollment(Long studentId, Long courseId, LocalDate enrollmentDate, 
                     EnrollmentStatus status, Integer credits, Boolean isPaid) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.credits = credits;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isCompleted() {
        return status == EnrollmentStatus.COMPLETED && completionDate != null;
    }

    public boolean isActive() {
        return status == EnrollmentStatus.ACTIVE && getIsActive();
    }

    public boolean isOverdue() {
        return paymentDueDate != null && paymentDueDate.isBefore(LocalDate.now()) && !isPaid;
    }

    public void complete() {
        this.status = EnrollmentStatus.COMPLETED;
        this.completionDate = LocalDate.now();
    }

    public void withdraw() {
        this.status = EnrollmentStatus.WITHDRAWN;
    }

    public void suspend() {
        this.status = EnrollmentStatus.SUSPENDED;
    }

    public void reactivate() {
        this.status = EnrollmentStatus.ACTIVE;
    }

    // Enums
    public enum EnrollmentStatus {
        ACTIVE, COMPLETED, WITHDRAWN, SUSPENDED, FAILED
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId, courseId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + getId() +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", status=" + status +
                ", grade=" + grade +
                ", isPaid=" + isPaid +
                '}';
    }
}
