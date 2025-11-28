-- Create students table
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    student_number VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    phone_number VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    address VARCHAR(500) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    enrollment_date DATE NOT NULL,
    enrollment_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (enrollment_status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'GRADUATED', 'TRANSFERRED', 'DROPPED')),
    grade_level VARCHAR(50),
    program VARCHAR(100),
    major VARCHAR(100),
    gpa DECIMAL(3,2),
    profile_picture_url VARCHAR(500),
    notes VARCHAR(1000),
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(20),
    emergency_contact_relationship VARCHAR(200),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_students_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_students_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT uk_students_institution_number UNIQUE (institution_id, student_number)
);

-- Create indexes for students table
CREATE INDEX idx_students_institution_id ON students(institution_id);
CREATE INDEX idx_students_branch_id ON students(branch_id);
CREATE INDEX idx_students_student_number ON students(student_number);
CREATE INDEX idx_students_email ON students(email);
CREATE INDEX idx_students_enrollment_status ON students(enrollment_status);
CREATE INDEX idx_students_grade_level ON students(grade_level);
CREATE INDEX idx_students_program ON students(program);
CREATE INDEX idx_students_major ON students(major);
CREATE INDEX idx_students_gender ON students(gender);
CREATE INDEX idx_students_city ON students(city);
CREATE INDEX idx_students_state ON students(state);
CREATE INDEX idx_students_country ON students(country);
CREATE INDEX idx_students_enrollment_date ON students(enrollment_date);
CREATE INDEX idx_students_date_of_birth ON students(date_of_birth);
CREATE INDEX idx_students_gpa ON students(gpa);
CREATE INDEX idx_students_is_active ON students(is_active);
CREATE INDEX idx_students_created_date ON students(created_date);

-- Create composite indexes
CREATE INDEX idx_students_institution_active ON students(institution_id, is_active);
CREATE INDEX idx_students_branch_active ON students(branch_id, is_active);
CREATE INDEX idx_students_institution_status ON students(institution_id, enrollment_status);
CREATE INDEX idx_students_institution_grade ON students(institution_id, grade_level);
CREATE INDEX idx_students_institution_program ON students(institution_id, program);

-- Add comments for documentation
COMMENT ON TABLE students IS 'Student records in educational institutions';
COMMENT ON COLUMN students.id IS 'Primary key';
COMMENT ON COLUMN students.institution_id IS 'Reference to institution';
COMMENT ON COLUMN students.branch_id IS 'Reference to branch';
COMMENT ON COLUMN students.student_number IS 'Unique student number within institution';
COMMENT ON COLUMN students.first_name IS 'First name';
COMMENT ON COLUMN students.last_name IS 'Last name';
COMMENT ON COLUMN students.middle_name IS 'Middle name';
COMMENT ON COLUMN students.date_of_birth IS 'Date of birth';
COMMENT ON COLUMN students.gender IS 'Gender';
COMMENT ON COLUMN students.phone_number IS 'Phone number';
COMMENT ON COLUMN students.email IS 'Email address';
COMMENT ON COLUMN students.address IS 'Address';
COMMENT ON COLUMN students.city IS 'City';
COMMENT ON COLUMN students.state IS 'State/Province';
COMMENT ON COLUMN students.postal_code IS 'Postal/ZIP code';
COMMENT ON COLUMN students.country IS 'Country';
COMMENT ON COLUMN students.enrollment_date IS 'Enrollment date';
COMMENT ON COLUMN students.enrollment_status IS 'Current enrollment status';
COMMENT ON COLUMN students.grade_level IS 'Grade level';
COMMENT ON COLUMN students.program IS 'Academic program';
COMMENT ON COLUMN students.major IS 'Major field of study';
COMMENT ON COLUMN students.gpa IS 'Grade Point Average';
COMMENT ON COLUMN students.profile_picture_url IS 'Profile picture URL';
COMMENT ON COLUMN students.notes IS 'Additional notes';
COMMENT ON COLUMN students.emergency_contact_name IS 'Emergency contact name';
COMMENT ON COLUMN students.emergency_contact_phone IS 'Emergency contact phone';
COMMENT ON COLUMN students.emergency_contact_relationship IS 'Emergency contact relationship';
COMMENT ON COLUMN students.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN students.created_by IS 'User who created the record';
COMMENT ON COLUMN students.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN students.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN students.is_active IS 'Active status flag';
COMMENT ON COLUMN students.deleted_at IS 'Soft delete timestamp';
