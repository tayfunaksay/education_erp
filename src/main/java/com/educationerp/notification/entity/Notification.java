package com.educationerp.notification.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Notification entity for the Education ERP System
 * Represents notifications sent to users
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @Column(name = "branch_id")
    private Long branchId;

    @NotNull(message = "Recipient ID is required")
    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;

    @Size(max = 50, message = "Recipient type must not exceed 50 characters")
    @Column(name = "recipient_type", length = 50)
    private String recipientType;

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotBlank(message = "Message is required")
    @Size(max = 2000, message = "Message must not exceed 2000 characters")
    @Column(name = "message", nullable = false, length = 2000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType = NotificationType.INFO;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private NotificationChannel channel = NotificationChannel.EMAIL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NotificationStatus status = NotificationStatus.PENDING;

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

    @Column(name = "read_time")
    private LocalDateTime readTime;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Size(max = 100, message = "Sender name must not exceed 100 characters")
    @Column(name = "sender_name", length = 100)
    private String senderName;

    @Column(name = "sender_id")
    private Long senderId;

    @Size(max = 200, message = "Email address must not exceed 200 characters")
    @Column(name = "email_address", length = 200)
    private String emailAddress;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Size(max = 100, message = "Template name must not exceed 100 characters")
    @Column(name = "template_name", length = 100)
    private String templateName;

    @Size(max = 1000, message = "Template variables must not exceed 1000 characters")
    @Column(name = "template_variables", length = 1000)
    private String templateVariables;

    @Size(max = 100, message = "Priority must not exceed 100 characters")
    @Column(name = "priority", length = 100)
    private String priority = "NORMAL";

    @Size(max = 1000, message = "Error message must not exceed 1000 characters")
    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;

    @Column(name = "max_retries", nullable = false)
    private Integer maxRetries = 3;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public Notification() {
    }

    public Notification(Long institutionId, Long branchId, Long recipientId, String recipientType, 
                       String title, String message, NotificationType notificationType, 
                       NotificationChannel channel, NotificationStatus status, String senderName, 
                       Long senderId, String emailAddress, String phoneNumber) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.title = title;
        this.message = message;
        this.notificationType = notificationType;
        this.channel = channel;
        this.status = status;
        this.senderName = senderName;
        this.senderId = senderId;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
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

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public LocalDateTime getReadTime() {
        return readTime;
    }

    public void setReadTime(LocalDateTime readTime) {
        this.readTime = readTime;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateVariables() {
        return templateVariables;
    }

    public void setTemplateVariables(String templateVariables) {
        this.templateVariables = templateVariables;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business methods
    public boolean isPending() {
        return status == NotificationStatus.PENDING;
    }

    public boolean isSent() {
        return status == NotificationStatus.SENT;
    }

    public boolean isFailed() {
        return status == NotificationStatus.FAILED;
    }

    public boolean isDelivered() {
        return status == NotificationStatus.DELIVERED;
    }

    public boolean isRead() {
        return isRead != null && isRead;
    }

    public boolean isScheduled() {
        return scheduledTime != null && scheduledTime.isAfter(LocalDateTime.now());
    }

    public boolean canRetry() {
        return retryCount != null && maxRetries != null && retryCount < maxRetries;
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
        this.sentTime = LocalDateTime.now();
    }

    public void markAsDelivered() {
        this.status = NotificationStatus.DELIVERED;
    }

    public void markAsFailed(String errorMessage) {
        this.status = NotificationStatus.FAILED;
        this.errorMessage = errorMessage;
    }

    public void markAsRead() {
        this.isRead = true;
        this.readTime = LocalDateTime.now();
    }

    public void incrementRetryCount() {
        if (retryCount == null) {
            retryCount = 0;
        }
        retryCount++;
    }

    public void resetRetryCount() {
        this.retryCount = 0;
    }

    // Enums
    public enum NotificationType {
        INFO, WARNING, ERROR, SUCCESS, REMINDER, ALERT, ANNOUNCEMENT, PAYMENT_REMINDER, 
        EXAM_NOTIFICATION, COURSE_UPDATE, SYSTEM_MAINTENANCE, SECURITY_ALERT
    }

    public enum NotificationChannel {
        EMAIL, SMS, PUSH, IN_APP, WEBHOOK
    }

    public enum NotificationStatus {
        PENDING, SENT, DELIVERED, FAILED, CANCELLED, SCHEDULED
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Notification that = (Notification) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(recipientId, that.recipientId) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, recipientId, title);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", recipientId=" + recipientId +
                ", title='" + title + '\'' +
                ", notificationType=" + notificationType +
                ", channel=" + channel +
                ", status=" + status +
                ", isRead=" + isRead +
                '}';
    }
}
