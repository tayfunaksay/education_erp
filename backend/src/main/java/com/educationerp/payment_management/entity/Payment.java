package com.educationerp.payment_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Payment entity for the Education ERP System
 * Represents payments made by students
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "payments", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "payment_reference"})
       })
public class Payment extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotNull(message = "Student ID is required")
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotBlank(message = "Payment reference is required")
    @Size(max = 50, message = "Payment reference must not exceed 50 characters")
    @Column(name = "payment_reference", nullable = false, length = 50)
    private String paymentReference;

    @NotNull(message = "Amount is required")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "tax_amount", precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "final_amount", precision = 10, scale = 2)
    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType = PaymentType.COURSE_FEE;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @NotNull(message = "Payment date is required")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "processed_date")
    private LocalDateTime processedDate;

    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Column(name = "receipt_number", length = 50)
    private String receiptNumber;

    @Column(name = "installment_plan_id")
    private Long installmentPlanId;

    @Column(name = "installment_number")
    private Integer installmentNumber;

    @Column(name = "total_installments")
    private Integer totalInstallments;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "package_id")
    private Long packageId;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "processed_by")
    private String processedBy;

    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount;

    @Column(name = "refund_date")
    private LocalDate refundDate;

    @Size(max = 1000, message = "Refund reason must not exceed 1000 characters")
    @Column(name = "refund_reason", length = 1000)
    private String refundReason;

    // Constructors
    public Payment() {
    }

    public Payment(Long institutionId, Long branchId, Long studentId, String paymentReference, 
                   BigDecimal amount, PaymentType paymentType, PaymentMethod paymentMethod, 
                   PaymentStatus status, LocalDate paymentDate, LocalDate dueDate) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.studentId = studentId;
        this.paymentReference = paymentReference;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
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

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(LocalDateTime processedDate) {
        this.processedDate = processedDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long getInstallmentPlanId() {
        return installmentPlanId;
    }

    public void setInstallmentPlanId(Long installmentPlanId) {
        this.installmentPlanId = installmentPlanId;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    // Business methods
    public boolean isPaid() {
        return status == PaymentStatus.COMPLETED;
    }

    public boolean isPending() {
        return status == PaymentStatus.PENDING;
    }

    public boolean isFailed() {
        return status == PaymentStatus.FAILED;
    }

    public boolean isRefunded() {
        return status == PaymentStatus.REFUNDED;
    }

    public boolean isOverdue() {
        return dueDate != null && dueDate.isBefore(LocalDate.now()) && !isPaid();
    }

    public boolean isInstallment() {
        return installmentPlanId != null && installmentNumber != null;
    }

    public void markAsCompleted() {
        this.status = PaymentStatus.COMPLETED;
        this.processedDate = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }

    public void markAsRefunded(BigDecimal refundAmount, String refundReason) {
        this.status = PaymentStatus.REFUNDED;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
        this.refundDate = LocalDate.now();
    }

    public void calculateFinalAmount() {
        BigDecimal finalAmount = amount;
        if (discountAmount != null) {
            finalAmount = finalAmount.subtract(discountAmount);
        }
        if (taxAmount != null) {
            finalAmount = finalAmount.add(taxAmount);
        }
        this.finalAmount = finalAmount;
    }

    // Enums
    public enum PaymentType {
        COURSE_FEE, BOOK_FEE, EXAM_FEE, TRANSPORT_FEE, HOSTEL_FEE, LIBRARY_FEE, 
        LAB_FEE, SPORTS_FEE, ACTIVITY_FEE, LATE_FEE, PENALTY, REFUND, OTHER
    }

    public enum PaymentMethod {
        CASH, BANK_TRANSFER, CREDIT_CARD, DEBIT_CARD, CHECK, ONLINE_PAYMENT, 
        MOBILE_PAYMENT, INSTALLMENT, SCHOLARSHIP, GRANT, OTHER
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED, REFUNDED, PARTIAL
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(institutionId, payment.institutionId) &&
                Objects.equals(paymentReference, payment.paymentReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, paymentReference);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", studentId=" + studentId +
                ", paymentReference='" + paymentReference + '\'' +
                ", amount=" + amount +
                ", paymentType=" + paymentType +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
