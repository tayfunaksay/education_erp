-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20),
    role VARCHAR(20) NOT NULL CHECK (role IN ('SUPER_ADMIN', 'INSTITUTION_ADMIN', 'BRANCH_MANAGER', 'COURSE_MANAGER', 'ACCOUNTANT', 'REPORT_VIEWER', 'TEACHER', 'STUDENT', 'PARENT')),
    is_locked BOOLEAN NOT NULL DEFAULT FALSE,
    failed_login_attempts INTEGER NOT NULL DEFAULT 0,
    last_login_date TIMESTAMP,
    password_changed_date TIMESTAMP,
    must_change_password BOOLEAN NOT NULL DEFAULT FALSE,
    profile_picture_url VARCHAR(500),
    institution_id BIGINT,
    branch_id BIGINT,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_institution_id ON users(institution_id);
CREATE INDEX idx_users_branch_id ON users(branch_id);
CREATE INDEX idx_users_is_active ON users(is_active);
CREATE INDEX idx_users_created_date ON users(created_date);

-- Create composite indexes
CREATE INDEX idx_users_institution_active ON users(institution_id, is_active);
CREATE INDEX idx_users_branch_active ON users(branch_id, is_active);
CREATE INDEX idx_users_role_active ON users(role, is_active);

-- Add comments for documentation
COMMENT ON TABLE users IS 'User accounts for the Education ERP System';
COMMENT ON COLUMN users.id IS 'Primary key';
COMMENT ON COLUMN users.username IS 'Unique username for login';
COMMENT ON COLUMN users.email IS 'Unique email address';
COMMENT ON COLUMN users.password IS 'Encrypted password';
COMMENT ON COLUMN users.first_name IS 'User first name';
COMMENT ON COLUMN users.last_name IS 'User last name';
COMMENT ON COLUMN users.phone_number IS 'User phone number';
COMMENT ON COLUMN users.role IS 'User role in the system';
COMMENT ON COLUMN users.is_locked IS 'Account lock status';
COMMENT ON COLUMN users.failed_login_attempts IS 'Number of consecutive failed login attempts';
COMMENT ON COLUMN users.last_login_date IS 'Last successful login timestamp';
COMMENT ON COLUMN users.password_changed_date IS 'Password last changed timestamp';
COMMENT ON COLUMN users.must_change_password IS 'Flag indicating if user must change password';
COMMENT ON COLUMN users.profile_picture_url IS 'URL to user profile picture';
COMMENT ON COLUMN users.institution_id IS 'Reference to institution';
COMMENT ON COLUMN users.branch_id IS 'Reference to branch';
COMMENT ON COLUMN users.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN users.created_by IS 'User who created the record';
COMMENT ON COLUMN users.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN users.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN users.is_active IS 'Soft delete flag';
COMMENT ON COLUMN users.deleted_at IS 'Soft delete timestamp';
