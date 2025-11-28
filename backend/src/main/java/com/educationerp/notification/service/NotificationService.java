package com.educationerp.notification.service;

import com.educationerp.notification.entity.Notification;
import com.educationerp.notification.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Service class for Notification operations
 * Handles sending notifications via different channels
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Service
@Transactional
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send email notification
     */
    @Async
    public CompletableFuture<Void> sendEmailNotification(Notification notification) {
        try {
            logger.info("Sending email notification to: {}", notification.getEmailAddress());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getEmailAddress());
            message.setSubject(notification.getTitle());
            message.setText(notification.getMessage());
            message.setFrom("noreply@educationerp.com");

            mailSender.send(message);

            notification.markAsSent();
            notificationRepository.save(notification);

            logger.info("Email notification sent successfully to: {}", notification.getEmailAddress());

        } catch (Exception e) {
            logger.error("Failed to send email notification to: {}", notification.getEmailAddress(), e);
            notification.markAsFailed(e.getMessage());
            notificationRepository.save(notification);
        }

        return CompletableFuture.completedFuture(null);
    }

    /**
     * Send SMS notification (placeholder implementation)
     */
    @Async
    public CompletableFuture<Void> sendSmsNotification(Notification notification) {
        try {
            logger.info("Sending SMS notification to: {}", notification.getPhoneNumber());

            // TODO: Implement actual SMS service integration
            // For now, just simulate SMS sending
            Thread.sleep(1000);

            notification.markAsSent();
            notificationRepository.save(notification);

            logger.info("SMS notification sent successfully to: {}", notification.getPhoneNumber());

        } catch (Exception e) {
            logger.error("Failed to send SMS notification to: {}", notification.getPhoneNumber(), e);
            notification.markAsFailed(e.getMessage());
            notificationRepository.save(notification);
        }

        return CompletableFuture.completedFuture(null);
    }

    /**
     * Send push notification (placeholder implementation)
     */
    @Async
    public CompletableFuture<Void> sendPushNotification(Notification notification) {
        try {
            logger.info("Sending push notification to user: {}", notification.getRecipientId());

            // TODO: Implement actual push notification service integration
            // For now, just simulate push notification sending
            Thread.sleep(500);

            notification.markAsSent();
            notificationRepository.save(notification);

            logger.info("Push notification sent successfully to user: {}", notification.getRecipientId());

        } catch (Exception e) {
            logger.error("Failed to send push notification to user: {}", notification.getRecipientId(), e);
            notification.markAsFailed(e.getMessage());
            notificationRepository.save(notification);
        }

        return CompletableFuture.completedFuture(null);
    }

    /**
     * Send notification based on channel
     */
    @Async
    public CompletableFuture<Void> sendNotification(Notification notification) {
        try {
            switch (notification.getChannel()) {
                case EMAIL:
                    return sendEmailNotification(notification);
                case SMS:
                    return sendSmsNotification(notification);
                case PUSH:
                    return sendPushNotification(notification);
                default:
                    logger.warn("Unsupported notification channel: {}", notification.getChannel());
                    notification.markAsFailed("Unsupported notification channel");
                    notificationRepository.save(notification);
                    return CompletableFuture.completedFuture(null);
            }
        } catch (Exception e) {
            logger.error("Failed to send notification", e);
            notification.markAsFailed(e.getMessage());
            notificationRepository.save(notification);
            return CompletableFuture.completedFuture(null);
        }
    }

    /**
     * Create and send notification
     */
    public Notification createAndSendNotification(Long institutionId, Long branchId, Long recipientId, 
                                                 String recipientType, String title, String message, 
                                                 Notification.NotificationType notificationType, 
                                                 Notification.NotificationChannel channel, 
                                                 String senderName, Long senderId, 
                                                 String emailAddress, String phoneNumber) {
        
        Notification notification = new Notification();
        notification.setInstitutionId(institutionId);
        notification.setBranchId(branchId);
        notification.setRecipientId(recipientId);
        notification.setRecipientType(recipientType);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setNotificationType(notificationType);
        notification.setChannel(channel);
        notification.setSenderName(senderName);
        notification.setSenderId(senderId);
        notification.setEmailAddress(emailAddress);
        notification.setPhoneNumber(phoneNumber);

        Notification savedNotification = notificationRepository.save(notification);

        // Send notification asynchronously
        sendNotification(savedNotification);

        return savedNotification;
    }

    /**
     * Send payment reminder notification
     */
    public void sendPaymentReminder(Long studentId, String studentName, String studentEmail, 
                                   String studentPhone, BigDecimal amount, LocalDateTime dueDate) {
        
        String title = "Payment Reminder";
        String message = ("Dear %s, this is a reminder that your payment of $%.2f is due on %s. " +
                "Please make the payment to avoid any late fees.").formatted(
                studentName, amount, dueDate.toLocalDate());

        createAndSendNotification(null, null, studentId, "STUDENT", title, message, 
                                Notification.NotificationType.PAYMENT_REMINDER, 
                                Notification.NotificationChannel.EMAIL, 
                                "System", null, studentEmail, studentPhone);
    }

    /**
     * Send exam notification
     */
    public void sendExamNotification(Long studentId, String studentName, String studentEmail, 
                                    String studentPhone, String examName, LocalDateTime examDate) {
        
        String title = "Exam Notification";
        String message = ("Dear %s, you have an exam '%s' scheduled on %s. " +
                "Please arrive 15 minutes early and bring your student ID.").formatted(
                studentName, examName, examDate.toLocalDate());

        createAndSendNotification(null, null, studentId, "STUDENT", title, message, 
                                Notification.NotificationType.EXAM_NOTIFICATION, 
                                Notification.NotificationChannel.EMAIL, 
                                "System", null, studentEmail, studentPhone);
    }

    /**
     * Send course update notification
     */
    public void sendCourseUpdateNotification(Long studentId, String studentName, String studentEmail, 
                                           String studentPhone, String courseName, String updateMessage) {
        
        String title = "Course Update";
        String message = "Dear %s, there is an update for your course '%s': %s".formatted(
                studentName, courseName, updateMessage);

        createAndSendNotification(null, null, studentId, "STUDENT", title, message, 
                                Notification.NotificationType.COURSE_UPDATE, 
                                Notification.NotificationChannel.EMAIL, 
                                "System", null, studentEmail, studentPhone);
    }

    /**
     * Send system maintenance notification
     */
    public void sendSystemMaintenanceNotification(Long institutionId, String maintenanceMessage, 
                                                 LocalDateTime maintenanceStart, LocalDateTime maintenanceEnd) {
        
        String message = ("System maintenance is scheduled from %s to %s. " +
                "During this time, the system may be unavailable. %s").formatted(
                maintenanceStart.toLocalDate(), maintenanceEnd.toLocalDate(), maintenanceMessage);

        // Send to all users in the institution
        // TODO: Implement bulk notification sending
        logger.info("System maintenance notification: {}", message);
    }

    /**
     * Get notifications for user
     */
    @Transactional(readOnly = true)
    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByRecipientIdAndIsActiveTrueOrderByCreatedDateDesc(userId);
    }

    /**
     * Mark notification as read
     */
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        notification.markAsRead();
        notificationRepository.save(notification);
    }

    /**
     * Get unread notification count for user
     */
    @Transactional(readOnly = true)
    public long getUnreadNotificationCount(Long userId) {
        return notificationRepository.countByRecipientIdAndIsReadFalseAndIsActiveTrue(userId);
    }
}
