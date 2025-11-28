package com.educationerp.payment_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Installment Plan entity for the Education ERP System
 * Represents installment payment plans for students
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "installment_plans", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "plan_code"})
       })
public class InstallmentPlan extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotBlank(message = "Plan code is required")
    @Size(max = 50, message = "Plan code must not exceed 50 characters")
    @Column(name = "plan_code", nullable = false, length = 50)
    private String planCode;

    @NotBlank(message = "Plan name is required")
    @Size(max = 200, message = "Plan name must not exceed 200 characters")
    @Column(name = "plan_name", nullable = false, length = 200)
    private String planName;

    @NotNull(message = "Total amount is required")
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "down_payment", precision = 10, scale = 2)
    private BigDecimal downPayment;

    @Column(name = "installment_amount", precision = 10, scale = 2)
    private BigDecimal installmentAmount;

    @NotNull(message = "Number of installments is required")
    @Column(name = "number_of_installments", nullable = false)
    private Integer numberOfInstallments;

    @Column(name = "paid_installments", nullable = false)
    private Integer paidInstallments = 0;

    @Column(name = "remaining_installments", nullable = false)
    private Integer remainingInstallments;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency", nullable = false)
    private InstallmentFrequency frequency = InstallmentFrequency.MONTHLY;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InstallmentStatus status = InstallmentStatus.ACTIVE;

    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "total_interest", precision = 10, scale = 2)
    private BigDecimal totalInterest;

    @Column(name = "late_fee_percentage", precision = 5, scale = 2)
    private BigDecimal lateFeePercentage;

    @Column(name = "late_fee_amount", precision = 10, scale = 2)
    private BigDecimal lateFeeAmount;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "package_id")
    private Long packageId;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 1000, message = "Terms and conditions must not exceed 1000 characters")
    @Column(name = "terms_and_conditions", length = 1000)
    private String termsAndConditions;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public InstallmentPlan() {
    }

    public InstallmentPlan(Long institutionId, Long branchId, Long studentId, String planCode, 
                          String planName, BigDecimal totalAmount, BigDecimal downPayment, 
                          BigDecimal installmentAmount, Integer numberOfInstallments, LocalDate startDate, 
                          LocalDate endDate, InstallmentFrequency frequency, InstallmentStatus status) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.studentId = studentId;
        this.planCode = planCode;
        this.planName = planName;
        this.totalAmount = totalAmount;
        this.downPayment = downPayment;
        this.installmentAmount = installmentAmount;
        this.numberOfInstallments = numberOfInstallments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.status = status;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(BigDecimal installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getPaidInstallments() {
        return paidInstallments;
    }

    public void setPaidInstallments(Integer paidInstallments) {
        this.paidInstallments = paidInstallments;
    }

    public Integer getRemainingInstallments() {
        return remainingInstallments;
    }

    public void setRemainingInstallments(Integer remainingInstallments) {
        this.remainingInstallments = remainingInstallments;
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

    public InstallmentFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(InstallmentFrequency frequency) {
        this.frequency = frequency;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    public void setStatus(InstallmentStatus status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getLateFeePercentage() {
        return lateFeePercentage;
    }

    public void setLateFeePercentage(BigDecimal lateFeePercentage) {
        this.lateFeePercentage = lateFeePercentage;
    }

    public BigDecimal getLateFeeAmount() {
        return lateFeeAmount;
    }

    public void setLateFeeAmount(BigDecimal lateFeeAmount) {
        this.lateFeeAmount = lateFeeAmount;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isActive() {
        return status == InstallmentStatus.ACTIVE && getIsActive();
    }

    public boolean isCompleted() {
        return status == InstallmentStatus.COMPLETED;
    }

    public boolean isOverdue() {
        return endDate != null && endDate.isBefore(LocalDate.now()) && !isCompleted();
    }

    public boolean isPaidOff() {
        return paidInstallments != null && numberOfInstallments != null && 
               paidInstallments >= numberOfInstallments;
    }

    public BigDecimal getPaidAmount() {
        BigDecimal paidAmount = BigDecimal.ZERO;
        if (downPayment != null) {
            paidAmount = paidAmount.add(downPayment);
        }
        if (installmentAmount != null && paidInstallments != null) {
            paidAmount = paidAmount.add(installmentAmount.multiply(new BigDecimal(paidInstallments)));
        }
        return paidAmount;
    }

    public BigDecimal getRemainingAmount() {
        return totalAmount.subtract(getPaidAmount());
    }

    public void incrementPaidInstallments() {
        if (paidInstallments == null) {
            paidInstallments = 0;
        }
        paidInstallments++;
        updateRemainingInstallments();
    }

    public void updateRemainingInstallments() {
        if (numberOfInstallments != null && paidInstallments != null) {
            remainingInstallments = numberOfInstallments - paidInstallments;
        }
    }

    public void markAsCompleted() {
        this.status = InstallmentStatus.COMPLETED;
        this.endDate = LocalDate.now();
    }

    public void markAsCancelled() {
        this.status = InstallmentStatus.CANCELLED;
    }

    public void markAsOverdue() {
        this.status = InstallmentStatus.OVERDUE;
    }

    public void calculateInstallmentAmount() {
        if (totalAmount != null && numberOfInstallments != null) {
            BigDecimal amountToDivide = totalAmount;
            if (downPayment != null) {
                amountToDivide = amountToDivide.subtract(downPayment);
            }
            this.installmentAmount = amountToDivide.divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.HALF_UP);
        }
    }

    // Enums
    public enum InstallmentFrequency {
        WEEKLY, MONTHLY, QUARTERLY, SEMESTERLY, YEARLY
    }

    public enum InstallmentStatus {
        ACTIVE, COMPLETED, CANCELLED, OVERDUE, SUSPENDED
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InstallmentPlan that = (InstallmentPlan) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(planCode, that.planCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, planCode);
    }

    @Override
    public String toString() {
        return "InstallmentPlan{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", studentId=" + studentId +
                ", planCode='" + planCode + '\'' +
                ", planName='" + planName + '\'' +
                ", totalAmount=" + totalAmount +
                ", numberOfInstallments=" + numberOfInstallments +
                ", paidInstallments=" + paidInstallments +
                ", status=" + status +
                '}';
    }
}
