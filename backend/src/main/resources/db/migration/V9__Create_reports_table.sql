-- Create reports table
CREATE TABLE reports (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    report_code VARCHAR(50) NOT NULL,
    report_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    report_type VARCHAR(20) NOT NULL DEFAULT 'STANDARD' CHECK (report_type IN ('STANDARD', 'CUSTOM', 'SCHEDULED', 'AD_HOC', 'DASHBOARD', 'ANALYTICS')),
    category VARCHAR(20) NOT NULL DEFAULT 'STUDENT' CHECK (category IN ('STUDENT', 'COURSE', 'PAYMENT', 'INVENTORY', 'USER', 'INSTITUTION', 'FINANCIAL', 'ACADEMIC', 'ATTENDANCE', 'EXAM', 'GRADES', 'ENROLLMENT', 'REPORTS', 'SYSTEM')),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'ARCHIVED', 'DRAFT', 'GENERATING', 'ERROR')),
    is_scheduled BOOLEAN NOT NULL DEFAULT FALSE,
    schedule_cron VARCHAR(100),
    last_generated_date TIMESTAMP,
    next_scheduled_date TIMESTAMP,
    generated_by VARCHAR(100),
    generation_time_ms BIGINT,
    record_count BIGINT,
    file_path VARCHAR(500),
    file_format VARCHAR(50),
    file_size_bytes BIGINT,
    parameters VARCHAR(1000),
    filters VARCHAR(1000),
    columns VARCHAR(1000),
    sort_order VARCHAR(100),
    is_public BOOLEAN NOT NULL DEFAULT FALSE,
    access_roles VARCHAR(1000),
    notes VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_reports_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_reports_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT uk_reports_institution_code UNIQUE (institution_id, report_code)
);

-- Create indexes for reports table
CREATE INDEX idx_reports_institution_id ON reports(institution_id);
CREATE INDEX idx_reports_branch_id ON reports(branch_id);
CREATE INDEX idx_reports_report_code ON reports(report_code);
CREATE INDEX idx_reports_report_type ON reports(report_type);
CREATE INDEX idx_reports_category ON reports(category);
CREATE INDEX idx_reports_status ON reports(status);
CREATE INDEX idx_reports_is_scheduled ON reports(is_scheduled);
CREATE INDEX idx_reports_schedule_cron ON reports(schedule_cron);
CREATE INDEX idx_reports_last_generated_date ON reports(last_generated_date);
CREATE INDEX idx_reports_next_scheduled_date ON reports(next_scheduled_date);
CREATE INDEX idx_reports_generated_by ON reports(generated_by);
CREATE INDEX idx_reports_file_format ON reports(file_format);
CREATE INDEX idx_reports_is_public ON reports(is_public);
CREATE INDEX idx_reports_is_active ON reports(is_active);
CREATE INDEX idx_reports_created_date ON reports(created_date);

-- Create composite indexes
CREATE INDEX idx_reports_institution_active ON reports(institution_id, is_active);
CREATE INDEX idx_reports_branch_active ON reports(branch_id, is_active);
CREATE INDEX idx_reports_institution_status ON reports(institution_id, status);
CREATE INDEX idx_reports_institution_type ON reports(institution_id, report_type);
CREATE INDEX idx_reports_institution_category ON reports(institution_id, category);
CREATE INDEX idx_reports_institution_scheduled ON reports(institution_id, is_scheduled);
CREATE INDEX idx_reports_scheduled_status ON reports(is_scheduled, status);

-- Add comments for documentation
COMMENT ON TABLE reports IS 'Generated reports and analytics';
COMMENT ON COLUMN reports.id IS 'Primary key';
COMMENT ON COLUMN reports.institution_id IS 'Reference to institution';
COMMENT ON COLUMN reports.branch_id IS 'Reference to branch';
COMMENT ON COLUMN reports.report_code IS 'Report code (unique within institution)';
COMMENT ON COLUMN reports.report_name IS 'Report name';
COMMENT ON COLUMN reports.description IS 'Report description';
COMMENT ON COLUMN reports.report_type IS 'Type of report';
COMMENT ON COLUMN reports.category IS 'Report category';
COMMENT ON COLUMN reports.status IS 'Report status';
COMMENT ON COLUMN reports.is_scheduled IS 'Scheduled report flag';
COMMENT ON COLUMN reports.schedule_cron IS 'Cron expression for scheduling';
COMMENT ON COLUMN reports.last_generated_date IS 'Last generation timestamp';
COMMENT ON COLUMN reports.next_scheduled_date IS 'Next scheduled generation timestamp';
COMMENT ON COLUMN reports.generated_by IS 'User who generated the report';
COMMENT ON COLUMN reports.generation_time_ms IS 'Generation time in milliseconds';
COMMENT ON COLUMN reports.record_count IS 'Number of records in report';
COMMENT ON COLUMN reports.file_path IS 'File path of generated report';
COMMENT ON COLUMN reports.file_format IS 'File format (PDF, Excel, CSV, etc.)';
COMMENT ON COLUMN reports.file_size_bytes IS 'File size in bytes';
COMMENT ON COLUMN reports.parameters IS 'Report parameters in JSON format';
COMMENT ON COLUMN reports.filters IS 'Applied filters in JSON format';
COMMENT ON COLUMN reports.columns IS 'Selected columns in JSON format';
COMMENT ON COLUMN reports.sort_order IS 'Sort order specification';
COMMENT ON COLUMN reports.is_public IS 'Public access flag';
COMMENT ON COLUMN reports.access_roles IS 'Allowed access roles';
COMMENT ON COLUMN reports.notes IS 'Additional notes';
COMMENT ON COLUMN reports.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN reports.created_by IS 'User who created the record';
COMMENT ON COLUMN reports.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN reports.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN reports.is_active IS 'Active status flag';
COMMENT ON COLUMN reports.deleted_at IS 'Soft delete timestamp';
