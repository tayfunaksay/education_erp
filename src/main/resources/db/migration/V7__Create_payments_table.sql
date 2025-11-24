-- Create payments table
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    student_id BIGINT NOT NULL,
    payment_reference VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    discount_amount DECIMAL(10,2),
    tax_amount DECIMAL(10,2),
    final_amount DECIMAL(10,2),
    payment_type VARCHAR(20) NOT NULL DEFAULT 'COURSE_FEE' CHECK (payment_type IN ('COURSE_FEE', 'BOOK_FEE', 'EXAM_FEE', 'TRANSPORT_FEE', 'HOSTEL_FEE', 'LIBRARY_FEE', 'LAB_FEE', 'SPORTS_FEE', 'ACTIVITY_FEE', 'LATE_FEE', 'PENALTY', 'REFUND', 'OTHER')),
    payment_method VARCHAR(20) NOT NULL DEFAULT 'CASH' CHECK (payment_method IN ('CASH', 'BANK_TRANSFER', 'CREDIT_CARD', 'DEBIT_CARD', 'CHECK', 'ONLINE_PAYMENT', 'MOBILE_PAYMENT', 'INSTALLMENT', 'SCHOLARSHIP', 'GRANT', 'OTHER')),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'CANCELLED', 'REFUNDED', 'PARTIAL')),
    payment_date DATE NOT NULL,
    due_date DATE,
    processed_date TIMESTAMP,
    transaction_id VARCHAR(100),
    receipt_number VARCHAR(50),
    installment_plan_id BIGINT,
    installment_number INTEGER,
    total_installments INTEGER,
    course_id BIGINT,
    product_id BIGINT,
    package_id BIGINT,
    description VARCHAR(1000),
    notes VARCHAR(1000),
    processed_by VARCHAR(100),
    refund_amount DECIMAL(10,2),
    refund_date DATE,
    refund_reason VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_payments_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_payments_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT fk_payments_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT uk_payments_institution_reference UNIQUE (institution_id, payment_reference)
);

-- Create indexes for payments table
CREATE INDEX idx_payments_institution_id ON payments(institution_id);
CREATE INDEX idx_payments_branch_id ON payments(branch_id);
CREATE INDEX idx_payments_student_id ON payments(student_id);
CREATE INDEX idx_payments_payment_reference ON payments(payment_reference);
CREATE INDEX idx_payments_payment_type ON payments(payment_type);
CREATE INDEX idx_payments_payment_method ON payments(payment_method);
CREATE INDEX idx_payments_status ON payments(status);
CREATE INDEX idx_payments_payment_date ON payments(payment_date);
CREATE INDEX idx_payments_due_date ON payments(due_date);
CREATE INDEX idx_payments_processed_date ON payments(processed_date);
CREATE INDEX idx_payments_transaction_id ON payments(transaction_id);
CREATE INDEX idx_payments_receipt_number ON payments(receipt_number);
CREATE INDEX idx_payments_installment_plan_id ON payments(installment_plan_id);
CREATE INDEX idx_payments_course_id ON payments(course_id);
CREATE INDEX idx_payments_product_id ON payments(product_id);
CREATE INDEX idx_payments_package_id ON payments(package_id);
CREATE INDEX idx_payments_amount ON payments(amount);
CREATE INDEX idx_payments_final_amount ON payments(final_amount);
CREATE INDEX idx_payments_is_active ON payments(is_active);
CREATE INDEX idx_payments_created_date ON payments(created_date);

-- Create composite indexes
CREATE INDEX idx_payments_institution_active ON payments(institution_id, is_active);
CREATE INDEX idx_payments_branch_active ON payments(branch_id, is_active);
CREATE INDEX idx_payments_student_active ON payments(student_id, is_active);
CREATE INDEX idx_payments_institution_status ON payments(institution_id, status);
CREATE INDEX idx_payments_student_status ON payments(student_id, status);
CREATE INDEX idx_payments_institution_type ON payments(institution_id, payment_type);
CREATE INDEX idx_payments_student_type ON payments(student_id, payment_type);
CREATE INDEX idx_payments_institution_date ON payments(institution_id, payment_date);
CREATE INDEX idx_payments_student_date ON payments(student_id, payment_date);

-- Add comments for documentation
COMMENT ON TABLE payments IS 'Payment transactions for students';
COMMENT ON COLUMN payments.id IS 'Primary key';
COMMENT ON COLUMN payments.institution_id IS 'Reference to institution';
COMMENT ON COLUMN payments.branch_id IS 'Reference to branch';
COMMENT ON COLUMN payments.student_id IS 'Reference to student';
COMMENT ON COLUMN payments.payment_reference IS 'Payment reference number (unique within institution)';
COMMENT ON COLUMN payments.amount IS 'Payment amount';
COMMENT ON COLUMN payments.discount_amount IS 'Discount amount';
COMMENT ON COLUMN payments.tax_amount IS 'Tax amount';
COMMENT ON COLUMN payments.final_amount IS 'Final amount after discounts and taxes';
COMMENT ON COLUMN payments.payment_type IS 'Type of payment';
COMMENT ON COLUMN payments.payment_method IS 'Payment method used';
COMMENT ON COLUMN payments.status IS 'Payment status';
COMMENT ON COLUMN payments.payment_date IS 'Payment date';
COMMENT ON COLUMN payments.due_date IS 'Due date for payment';
COMMENT ON COLUMN payments.processed_date IS 'Date when payment was processed';
COMMENT ON COLUMN payments.transaction_id IS 'External transaction ID';
COMMENT ON COLUMN payments.receipt_number IS 'Receipt number';
COMMENT ON COLUMN payments.installment_plan_id IS 'Reference to installment plan';
COMMENT ON COLUMN payments.installment_number IS 'Installment number';
COMMENT ON COLUMN payments.total_installments IS 'Total number of installments';
COMMENT ON COLUMN payments.course_id IS 'Reference to course';
COMMENT ON COLUMN payments.product_id IS 'Reference to product';
COMMENT ON COLUMN payments.package_id IS 'Reference to package';
COMMENT ON COLUMN payments.description IS 'Payment description';
COMMENT ON COLUMN payments.notes IS 'Additional notes';
COMMENT ON COLUMN payments.processed_by IS 'User who processed the payment';
COMMENT ON COLUMN payments.refund_amount IS 'Refund amount';
COMMENT ON COLUMN payments.refund_date IS 'Refund date';
COMMENT ON COLUMN payments.refund_reason IS 'Refund reason';
COMMENT ON COLUMN payments.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN payments.created_by IS 'User who created the record';
COMMENT ON COLUMN payments.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN payments.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN payments.is_active IS 'Active status flag';
COMMENT ON COLUMN payments.deleted_at IS 'Soft delete timestamp';
