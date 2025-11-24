package com.educationerp.inventory.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Student Product Distribution entity for the Education ERP System
 * Tracks distribution of educational products to students
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "student_product_distributions", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"student_id", "product_id", "distribution_date"})
       })
public class StudentProductDistribution extends BaseEntity {

    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull(message = "Product ID is required")
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "package_id")
    private Long packageId;

    @NotNull(message = "Distribution date is required")
    @Column(name = "distribution_date", nullable = false)
    private LocalDate distributionDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "distribution_type", nullable = false)
    private DistributionType distributionType = DistributionType.SALE;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DistributionStatus status = DistributionStatus.ACTIVE;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "final_price", precision = 10, scale = 2)
    private BigDecimal finalPrice;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "rental_duration_days")
    private Integer rentalDurationDays;

    @Column(name = "rental_return_due_date")
    private LocalDate rentalReturnDueDate;

    @Column(name = "late_fee", precision = 10, scale = 2)
    private BigDecimal lateFee;

    @Column(name = "condition_on_distribution", length = 100)
    private String conditionOnDistribution;

    @Column(name = "condition_on_return", length = 100)
    private String conditionOnReturn;

    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public StudentProductDistribution() {
    }

    public StudentProductDistribution(Long studentId, Long productId, Long packageId, 
                                    LocalDate distributionDate, DistributionType distributionType, 
                                    DistributionStatus status, Integer quantity, BigDecimal unitPrice, 
                                    BigDecimal totalPrice, BigDecimal finalPrice, Boolean isPaid) {
        this.studentId = studentId;
        this.productId = productId;
        this.packageId = packageId;
        this.distributionDate = distributionDate;
        this.distributionType = distributionType;
        this.status = status;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.finalPrice = finalPrice;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDate distributionDate) {
        this.distributionDate = distributionDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public DistributionStatus getStatus() {
        return status;
    }

    public void setStatus(DistributionStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getRentalDurationDays() {
        return rentalDurationDays;
    }

    public void setRentalDurationDays(Integer rentalDurationDays) {
        this.rentalDurationDays = rentalDurationDays;
    }

    public LocalDate getRentalReturnDueDate() {
        return rentalReturnDueDate;
    }

    public void setRentalReturnDueDate(LocalDate rentalReturnDueDate) {
        this.rentalReturnDueDate = rentalReturnDueDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public String getConditionOnDistribution() {
        return conditionOnDistribution;
    }

    public void setConditionOnDistribution(String conditionOnDistribution) {
        this.conditionOnDistribution = conditionOnDistribution;
    }

    public String getConditionOnReturn() {
        return conditionOnReturn;
    }

    public void setConditionOnReturn(String conditionOnReturn) {
        this.conditionOnReturn = conditionOnReturn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isActive() {
        return status == DistributionStatus.ACTIVE && getIsActive();
    }

    public boolean isRental() {
        return distributionType == DistributionType.RENTAL;
    }

    public boolean isSale() {
        return distributionType == DistributionType.SALE;
    }

    public boolean isOverdue() {
        if (paymentDueDate != null && !isPaid) {
            return paymentDueDate.isBefore(LocalDate.now());
        }
        if (rentalReturnDueDate != null && isRental()) {
            return rentalReturnDueDate.isBefore(LocalDate.now());
        }
        return false;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markAsPaid() {
        this.isPaid = true;
        this.paymentDate = LocalDate.now();
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
        this.status = DistributionStatus.RETURNED;
    }

    public void markAsLost() {
        this.status = DistributionStatus.LOST;
    }

    public void markAsDamaged() {
        this.status = DistributionStatus.DAMAGED;
    }

    public void calculateFinalPrice() {
        if (totalPrice != null && discountAmount != null) {
            this.finalPrice = totalPrice.subtract(discountAmount);
        } else {
            this.finalPrice = totalPrice;
        }
    }

    public void calculateRentalReturnDueDate() {
        if (distributionDate != null && rentalDurationDays != null) {
            this.rentalReturnDueDate = distributionDate.plusDays(rentalDurationDays);
        }
    }

    // Enums
    public enum DistributionType {
        SALE, RENTAL, LOAN, FREE_DISTRIBUTION
    }

    public enum DistributionStatus {
        ACTIVE, RETURNED, LOST, DAMAGED, OVERDUE, CANCELLED
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentProductDistribution that = (StudentProductDistribution) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(distributionDate, that.distributionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId, productId, distributionDate);
    }

    @Override
    public String toString() {
        return "StudentProductDistribution{" +
                "id=" + getId() +
                ", studentId=" + studentId +
                ", productId=" + productId +
                ", distributionDate=" + distributionDate +
                ", distributionType=" + distributionType +
                ", status=" + status +
                ", quantity=" + quantity +
                ", finalPrice=" + finalPrice +
                ", isPaid=" + isPaid +
                '}';
    }
}
