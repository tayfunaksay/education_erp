package com.educationerp.inventory.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Educational Product entity for the Education ERP System
 * Represents books and educational resources
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "educational_products", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "isbn"}),
           @UniqueConstraint(columnNames = {"institution_id", "product_code"})
       })
public class EducationalProduct extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotBlank(message = "Product code is required")
    @Size(max = 50, message = "Product code must not exceed 50 characters")
    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;

    @NotBlank(message = "Product name is required")
    @Size(max = 200, message = "Product name must not exceed 200 characters")
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Size(max = 20, message = "ISBN must not exceed 20 characters")
    @Column(name = "isbn", length = 20)
    private String isbn;

    @Size(max = 100, message = "Publisher must not exceed 100 characters")
    @Column(name = "publisher", length = 100)
    private String publisher;

    @Size(max = 100, message = "Author must not exceed 100 characters")
    @Column(name = "author", length = 100)
    private String author;

    @Size(max = 50, message = "Grade level must not exceed 50 characters")
    @Column(name = "grade_level", length = 50)
    private String gradeLevel;

    @Size(max = 100, message = "Subject must not exceed 100 characters")
    @Column(name = "subject", length = 100)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType = ProductType.BOOK;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProductCategory category = ProductCategory.TEXTBOOK;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "purchase_price", precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "selling_price", precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Column(name = "rental_price", precision = 10, scale = 2)
    private BigDecimal rentalPrice;

    @Column(name = "current_stock", nullable = false)
    private Integer currentStock = 0;

    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock = 0;

    @Column(name = "maximum_stock")
    private Integer maximumStock;

    @Column(name = "reorder_point", nullable = false)
    private Integer reorderPoint = 0;

    @Column(name = "reorder_quantity")
    private Integer reorderQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(name = "is_rental_available", nullable = false)
    private Boolean isRentalAvailable = false;

    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory = false;

    @Column(name = "edition")
    private String edition;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Size(max = 100, message = "Language must not exceed 100 characters")
    @Column(name = "language", length = 100)
    private String language;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public EducationalProduct() {
    }

    public EducationalProduct(Long institutionId, Long branchId, String productCode, String productName, 
                             String isbn, String publisher, String author, String gradeLevel, String subject, 
                             ProductType productType, ProductCategory category, String description, 
                             BigDecimal purchasePrice, BigDecimal sellingPrice, BigDecimal rentalPrice, 
                             Integer currentStock, Integer minimumStock, Integer maximumStock, 
                             Integer reorderPoint, Integer reorderQuantity, ProductStatus status, 
                             Boolean isRentalAvailable, Boolean isMandatory) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.productCode = productCode;
        this.productName = productName;
        this.isbn = isbn;
        this.publisher = publisher;
        this.author = author;
        this.gradeLevel = gradeLevel;
        this.subject = subject;
        this.productType = productType;
        this.category = category;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.rentalPrice = rentalPrice;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
        this.maximumStock = maximumStock;
        this.reorderPoint = reorderPoint;
        this.reorderQuantity = reorderQuantity;
        this.status = status;
        this.isRentalAvailable = isRentalAvailable;
        this.isMandatory = isMandatory;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public Integer getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(Integer maximumStock) {
        this.maximumStock = maximumStock;
    }

    public Integer getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(Integer reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public Integer getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(Integer reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Boolean getIsRentalAvailable() {
        return isRentalAvailable;
    }

    public void setIsRentalAvailable(Boolean isRentalAvailable) {
        this.isRentalAvailable = isRentalAvailable;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        return status == ProductStatus.ACTIVE && getIsActive();
    }

    public boolean isInStock() {
        return currentStock != null && currentStock > 0;
    }

    public boolean isLowStock() {
        return currentStock != null && minimumStock != null && currentStock <= minimumStock;
    }

    public boolean needsReorder() {
        return currentStock != null && reorderPoint != null && currentStock <= reorderPoint;
    }

    public boolean isOverstocked() {
        return maximumStock != null && currentStock != null && currentStock > maximumStock;
    }

    public void addStock(Integer quantity) {
        if (currentStock == null) {
            currentStock = 0;
        }
        currentStock += quantity;
    }

    public void removeStock(Integer quantity) {
        if (currentStock == null) {
            currentStock = 0;
        }
        currentStock = Math.max(0, currentStock - quantity);
    }

    public void activate() {
        this.status = ProductStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
    }

    public void discontinue() {
        this.status = ProductStatus.DISCONTINUED;
    }

    // Enums
    public enum ProductType {
        BOOK, WORKBOOK, REFERENCE, DICTIONARY, ATLAS, SOFTWARE, HARDWARE, EQUIPMENT, SUPPLIES, OTHER
    }

    public enum ProductCategory {
        TEXTBOOK, REFERENCE_BOOK, WORKBOOK, DICTIONARY, ATLAS, SOFTWARE, HARDWARE, EQUIPMENT, SUPPLIES, 
        MOCK_EXAM, PRACTICE_TEST, STUDY_GUIDE, LITERATURE, SCIENCE_KIT, MATH_TOOLS, OTHER
    }

    public enum ProductStatus {
        ACTIVE, INACTIVE, DISCONTINUED, OUT_OF_STOCK
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EducationalProduct that = (EducationalProduct) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, productCode, isbn);
    }

    @Override
    public String toString() {
        return "EducationalProduct{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                ", currentStock=" + currentStock +
                ", isActive=" + getIsActive() +
                '}';
    }
}
