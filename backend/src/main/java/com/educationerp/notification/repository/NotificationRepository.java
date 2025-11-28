package com.educationerp.notification.repository;

import com.educationerp.notification.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Notification entity
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Find notifications by recipient ID
     */
    List<Notification> findByRecipientId(Long recipientId);

    /**
     * Find notifications by recipient ID and status
     */
    List<Notification> findByRecipientIdAndStatus(Long recipientId, String status);

    /**
     * Find unread notifications by recipient ID
     */
    List<Notification> findByRecipientIdAndIsReadFalse(Long recipientId);

    /**
     * Find notifications by type
     */
    List<Notification> findByNotificationType(String notificationType);

    /**
     * Find notifications by date range
     */
    @Query("SELECT n FROM Notification n WHERE n.createdDate BETWEEN :startDate AND :endDate")
    List<Notification> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                         @Param("endDate") LocalDateTime endDate);

    /**
     * Find all notifications by recipient ID with pagination
     */
    Page<Notification> findByRecipientId(Long recipientId, Pageable pageable);

    /**
     * Count unread notifications by recipient ID
     */
    long countByRecipientIdAndIsReadFalse(Long recipientId);

    /**
     * Find active notifications by recipient ID ordered by created date descending
     */
    List<Notification> findByRecipientIdAndIsActiveTrueOrderByCreatedDateDesc(Long recipientId);

    /**
     * Count unread active notifications by recipient ID
     */
    long countByRecipientIdAndIsReadFalseAndIsActiveTrue(Long recipientId);
}

