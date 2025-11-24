-- Create courses table
CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    course_code VARCHAR(20) NOT NULL,
    course_name VARCHAR(200) NOT NULL,
    description VARCHAR(1000),
    subject VARCHAR(100),
    grade_level VARCHAR(50),
    credits INTEGER,
    duration_hours INTEGER,
    max_students INTEGER,
    current_students INTEGER DEFAULT 0,
    start_date DATE NOT NULL,
    end_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'SUSPENDED', 'COMPLETED', 'CANCELLED', 'DRAFT')),
    type VARCHAR(20) NOT NULL DEFAULT 'REGULAR' CHECK (type IN ('REGULAR', 'INTENSIVE', 'WORKSHOP', 'SEMINAR', 'ONLINE', 'HYBRID', 'PRIVATE', 'GROUP')),
    fee DECIMAL(10,2),
    is_online BOOLEAN NOT NULL DEFAULT FALSE,
    location VARCHAR(500),
    instructor_name VARCHAR(100),
    instructor_id BIGINT,
    prerequisites VARCHAR(1000),
    learning_objectives VARCHAR(1000),
    course_materials VARCHAR(1000),
    assessment_methods VARCHAR(1000),
    notes VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_courses_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_courses_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT uk_courses_institution_code UNIQUE (institution_id, course_code)
);

-- Create indexes for courses table
CREATE INDEX idx_courses_institution_id ON courses(institution_id);
CREATE INDEX idx_courses_branch_id ON courses(branch_id);
CREATE INDEX idx_courses_course_code ON courses(course_code);
CREATE INDEX idx_courses_subject ON courses(subject);
CREATE INDEX idx_courses_grade_level ON courses(grade_level);
CREATE INDEX idx_courses_status ON courses(status);
CREATE INDEX idx_courses_type ON courses(type);
CREATE INDEX idx_courses_instructor_id ON courses(instructor_id);
CREATE INDEX idx_courses_start_date ON courses(start_date);
CREATE INDEX idx_courses_end_date ON courses(end_date);
CREATE INDEX idx_courses_fee ON courses(fee);
CREATE INDEX idx_courses_is_online ON courses(is_online);
CREATE INDEX idx_courses_is_active ON courses(is_active);
CREATE INDEX idx_courses_created_date ON courses(created_date);

-- Create composite indexes
CREATE INDEX idx_courses_institution_active ON courses(institution_id, is_active);
CREATE INDEX idx_courses_branch_active ON courses(branch_id, is_active);
CREATE INDEX idx_courses_institution_status ON courses(institution_id, status);
CREATE INDEX idx_courses_institution_type ON courses(institution_id, type);
CREATE INDEX idx_courses_institution_subject ON courses(institution_id, subject);
CREATE INDEX idx_courses_institution_grade ON courses(institution_id, grade_level);

-- Add comments for documentation
COMMENT ON TABLE courses IS 'Courses offered by educational institutions';
COMMENT ON COLUMN courses.id IS 'Primary key';
COMMENT ON COLUMN courses.institution_id IS 'Reference to institution';
COMMENT ON COLUMN courses.branch_id IS 'Reference to branch';
COMMENT ON COLUMN courses.course_code IS 'Course code (unique within institution)';
COMMENT ON COLUMN courses.course_name IS 'Course name';
COMMENT ON COLUMN courses.description IS 'Course description';
COMMENT ON COLUMN courses.subject IS 'Subject area';
COMMENT ON COLUMN courses.grade_level IS 'Grade level';
COMMENT ON COLUMN courses.credits IS 'Number of credits';
COMMENT ON COLUMN courses.duration_hours IS 'Duration in hours';
COMMENT ON COLUMN courses.max_students IS 'Maximum number of students';
COMMENT ON COLUMN courses.current_students IS 'Current number of enrolled students';
COMMENT ON COLUMN courses.start_date IS 'Course start date';
COMMENT ON COLUMN courses.end_date IS 'Course end date';
COMMENT ON COLUMN courses.status IS 'Course status';
COMMENT ON COLUMN courses.type IS 'Course type';
COMMENT ON COLUMN courses.fee IS 'Course fee';
COMMENT ON COLUMN courses.is_online IS 'Online course flag';
COMMENT ON COLUMN courses.location IS 'Course location';
COMMENT ON COLUMN courses.instructor_name IS 'Instructor name';
COMMENT ON COLUMN courses.instructor_id IS 'Reference to instructor user';
COMMENT ON COLUMN courses.prerequisites IS 'Course prerequisites';
COMMENT ON COLUMN courses.learning_objectives IS 'Learning objectives';
COMMENT ON COLUMN courses.course_materials IS 'Required course materials';
COMMENT ON COLUMN courses.assessment_methods IS 'Assessment methods';
COMMENT ON COLUMN courses.notes IS 'Additional notes';
COMMENT ON COLUMN courses.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN courses.created_by IS 'User who created the record';
COMMENT ON COLUMN courses.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN courses.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN courses.is_active IS 'Active status flag';
COMMENT ON COLUMN courses.deleted_at IS 'Soft delete timestamp';
