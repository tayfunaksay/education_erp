package com.educationerp.inventory.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Product Package entity for the Education ERP System
 * Represents bundled educational products with discounted pricing
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "product_packages", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "package_code"})
       })
public class ProductPackage extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotBlank(message = "Package code is required")
    @Size(max = 50, message = "Package code must not exceed 50 characters")
    @Column(name = "package_code", nullable = false, length = 50)
    private String packageCode;

    @NotBlank(message = "Package name is required")
    @Size(max = 200, message = "Package name must not exceed 200 characters")
    @Column(name = "package_name", nullable = false, length = 200)
    private String packageName;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "package_type", nullable = false)
    private PackageType packageType = PackageType.STANDARD;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "discounted_price", precision = 10, scale = 2)
    private BigDecimal discountedPrice;

    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PackageStatus status = PackageStatus.ACTIVE;

    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory = false;

    @Column(name = "is_rental_available", nullable = false)
    private Boolean isRentalAvailable = false;

    @Column(name = "rental_price", precision = 10, scale = 2)
    private BigDecimal rentalPrice;

    @Size(max = 50, message = "Grade level must not exceed 50 characters")
    @Column(name = "grade_level", length = 50)
    private String gradeLevel;

    @Size(max = 100, message = "Subject must not exceed 100 characters")
    @Column(name = "subject", length = 100)
    private String subject;

    @Size(max = 100, message = "Program must not exceed 100 characters")
    @Column(name = "program", length = 100)
    private String program;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public ProductPackage() {
    }

    public ProductPackage(Long institutionId, Long branchId, String packageCode, String packageName, 
                         String description, PackageType packageType, BigDecimal totalPrice, 
                         BigDecimal discountedPrice, BigDecimal discountPercentage, BigDecimal discountAmount, 
                         PackageStatus status, Boolean isMandatory, Boolean isRentalAvailable, 
                         BigDecimal rentalPrice, String gradeLevel, String subject, String program) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.packageCode = packageCode;
        this.packageName = packageName;
        this.description = description;
        this.packageType = packageType;
        this.totalPrice = totalPrice;
        this.discountedPrice = discountedPrice;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.status = status;
        this.isMandatory = isMandatory;
        this.isRentalAvailable = isRentalAvailable;
        this.rentalPrice = rentalPrice;
        this.gradeLevel = gradeLevel;
        this.subject = subject;
        this.program = program;
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

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Boolean getIsRentalAvailable() {
        return isRentalAvailable;
    }

    public void setIsRentalAvailable(Boolean isRentalAvailable) {
        this.isRentalAvailable = isRentalAvailable;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isActive() {
        return status == PackageStatus.ACTIVE && getIsActive();
    }

    public boolean hasDiscount() {
        return discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getEffectivePrice() {
        return discountedPrice != null ? discountedPrice : totalPrice;
    }

    public BigDecimal calculateDiscountAmount() {
        if (totalPrice != null && discountPercentage != null) {
            return totalPrice.multiply(discountPercentage.divide(new BigDecimal("100")));
        }
        return discountAmount;
    }

    public void activate() {
        this.status = PackageStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = PackageStatus.INACTIVE;
    }

    public void discontinue() {
        this.status = PackageStatus.DISCONTINUED;
    }

    // Enums
    public enum PackageType {
        STANDARD, PREMIUM, GOLD, PLATINUM, CUSTOM, MOCK_EXAM_PACKAGE, STUDY_PACKAGE, COMPLETE_PACKAGE
    }

    public enum PackageStatus {
        ACTIVE, INACTIVE, DISCONTINUED, DRAFT
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductPackage that = (ProductPackage) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(packageCode, that.packageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, packageCode);
    }

    @Override
    public String toString() {
        return "ProductPackage{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", packageCode='" + packageCode + '\'' +
                ", packageName='" + packageName + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", discountedPrice=" + discountedPrice +
                ", isActive=" + getIsActive() +
                '}';
    }
}
