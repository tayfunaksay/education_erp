-- Create notifications table
CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    recipient_id BIGINT NOT NULL,
    recipient_type VARCHAR(50),
    title VARCHAR(200) NOT NULL,
    message VARCHAR(2000) NOT NULL,
    notification_type VARCHAR(20) NOT NULL DEFAULT 'INFO' CHECK (notification_type IN ('INFO', 'WARNING', 'ERROR', 'SUCCESS', 'REMINDER', 'ALERT', 'ANNOUNCEMENT', 'PAYMENT_REMINDER', 'EXAM_NOTIFICATION', 'COURSE_UPDATE', 'SYSTEM_MAINTENANCE', 'SECURITY_ALERT')),
    channel VARCHAR(20) NOT NULL DEFAULT 'EMAIL' CHECK (channel IN ('EMAIL', 'SMS', 'PUSH', 'IN_APP', 'WEBHOOK')),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'SENT', 'DELIVERED', 'FAILED', 'CANCELLED', 'SCHEDULED')),
    scheduled_time TIMESTAMP,
    sent_time TIMESTAMP,
    read_time TIMESTAMP,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    sender_name VARCHAR(100),
    sender_id BIGINT,
    email_address VARCHAR(200),
    phone_number VARCHAR(20),
    template_name VARCHAR(100),
    template_variables VARCHAR(1000),
    priority VARCHAR(100) DEFAULT 'NORMAL',
    error_message VARCHAR(1000),
    retry_count INTEGER NOT NULL DEFAULT 0,
    max_retries INTEGER NOT NULL DEFAULT 3,
    notes VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_notifications_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_notifications_branch FOREIGN KEY (branch_id) REFERENCES branches(id)
);

-- Create indexes for notifications table
CREATE INDEX idx_notifications_institution_id ON notifications(institution_id);
CREATE INDEX idx_notifications_branch_id ON notifications(branch_id);
CREATE INDEX idx_notifications_recipient_id ON notifications(recipient_id);
CREATE INDEX idx_notifications_recipient_type ON notifications(recipient_type);
CREATE INDEX idx_notifications_notification_type ON notifications(notification_type);
CREATE INDEX idx_notifications_channel ON notifications(channel);
CREATE INDEX idx_notifications_status ON notifications(status);
CREATE INDEX idx_notifications_scheduled_time ON notifications(scheduled_time);
CREATE INDEX idx_notifications_sent_time ON notifications(sent_time);
CREATE INDEX idx_notifications_read_time ON notifications(read_time);
CREATE INDEX idx_notifications_is_read ON notifications(is_read);
CREATE INDEX idx_notifications_sender_id ON notifications(sender_id);
CREATE INDEX idx_notifications_email_address ON notifications(email_address);
CREATE INDEX idx_notifications_phone_number ON notifications(phone_number);
CREATE INDEX idx_notifications_template_name ON notifications(template_name);
CREATE INDEX idx_notifications_priority ON notifications(priority);
CREATE INDEX idx_notifications_retry_count ON notifications(retry_count);
CREATE INDEX idx_notifications_is_active ON notifications(is_active);
CREATE INDEX idx_notifications_created_date ON notifications(created_date);

-- Create composite indexes
CREATE INDEX idx_notifications_institution_active ON notifications(institution_id, is_active);
CREATE INDEX idx_notifications_branch_active ON notifications(branch_id, is_active);
CREATE INDEX idx_notifications_recipient_active ON notifications(recipient_id, is_active);
CREATE INDEX idx_notifications_recipient_read ON notifications(recipient_id, is_read);
CREATE INDEX idx_notifications_institution_status ON notifications(institution_id, status);
CREATE INDEX idx_notifications_recipient_status ON notifications(recipient_id, status);
CREATE INDEX idx_notifications_institution_type ON notifications(institution_id, notification_type);
CREATE INDEX idx_notifications_recipient_notification_type ON notifications(recipient_id, notification_type);
CREATE INDEX idx_notifications_institution_channel ON notifications(institution_id, channel);
CREATE INDEX idx_notifications_recipient_channel ON notifications(recipient_id, channel);

-- Add comments for documentation
COMMENT ON TABLE notifications IS 'Notifications sent to users';
COMMENT ON COLUMN notifications.id IS 'Primary key';
COMMENT ON COLUMN notifications.institution_id IS 'Reference to institution';
COMMENT ON COLUMN notifications.branch_id IS 'Reference to branch';
COMMENT ON COLUMN notifications.recipient_id IS 'Recipient user ID';
COMMENT ON COLUMN notifications.recipient_type IS 'Type of recipient (STUDENT, TEACHER, etc.)';
COMMENT ON COLUMN notifications.title IS 'Notification title';
COMMENT ON COLUMN notifications.message IS 'Notification message';
COMMENT ON COLUMN notifications.notification_type IS 'Type of notification';
COMMENT ON COLUMN notifications.channel IS 'Delivery channel';
COMMENT ON COLUMN notifications.status IS 'Notification status';
COMMENT ON COLUMN notifications.scheduled_time IS 'Scheduled delivery time';
COMMENT ON COLUMN notifications.sent_time IS 'Actual sent time';
COMMENT ON COLUMN notifications.read_time IS 'Time when notification was read';
COMMENT ON COLUMN notifications.is_read IS 'Read status flag';
COMMENT ON COLUMN notifications.sender_name IS 'Sender name';
COMMENT ON COLUMN notifications.sender_id IS 'Sender user ID';
COMMENT ON COLUMN notifications.email_address IS 'Recipient email address';
COMMENT ON COLUMN notifications.phone_number IS 'Recipient phone number';
COMMENT ON COLUMN notifications.template_name IS 'Template name used';
COMMENT ON COLUMN notifications.template_variables IS 'Template variables in JSON format';
COMMENT ON COLUMN notifications.priority IS 'Notification priority';
COMMENT ON COLUMN notifications.error_message IS 'Error message if delivery failed';
COMMENT ON COLUMN notifications.retry_count IS 'Number of retry attempts';
COMMENT ON COLUMN notifications.max_retries IS 'Maximum retry attempts';
COMMENT ON COLUMN notifications.notes IS 'Additional notes';
COMMENT ON COLUMN notifications.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN notifications.created_by IS 'User who created the record';
COMMENT ON COLUMN notifications.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN notifications.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN notifications.is_active IS 'Active status flag';
COMMENT ON COLUMN notifications.deleted_at IS 'Soft delete timestamp';
