-- Create branches table
CREATE TABLE branches (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    name VARCHAR(200) NOT NULL,
    code VARCHAR(20) NOT NULL,
    description VARCHAR(500),
    address VARCHAR(500) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    website_url VARCHAR(200),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    logo_url VARCHAR(500),
    settings_json VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_branches_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT uk_branches_institution_code UNIQUE (institution_id, code)
);

-- Create indexes for branches table
CREATE INDEX idx_branches_institution_id ON branches(institution_id);
CREATE INDEX idx_branches_code ON branches(code);
CREATE INDEX idx_branches_email ON branches(email);
CREATE INDEX idx_branches_city ON branches(city);
CREATE INDEX idx_branches_state ON branches(state);
CREATE INDEX idx_branches_country ON branches(country);
CREATE INDEX idx_branches_is_active ON branches(is_active);
CREATE INDEX idx_branches_created_date ON branches(created_date);

-- Create composite indexes
CREATE INDEX idx_branches_institution_active ON branches(institution_id, is_active);
CREATE INDEX idx_branches_city_active ON branches(city, is_active);
CREATE INDEX idx_branches_state_active ON branches(state, is_active);
CREATE INDEX idx_branches_country_active ON branches(country, is_active);

-- Add comments for documentation
COMMENT ON TABLE branches IS 'Branches of educational institutions';
COMMENT ON COLUMN branches.id IS 'Primary key';
COMMENT ON COLUMN branches.institution_id IS 'Reference to parent institution';
COMMENT ON COLUMN branches.name IS 'Branch name';
COMMENT ON COLUMN branches.code IS 'Branch code (unique within institution)';
COMMENT ON COLUMN branches.description IS 'Branch description';
COMMENT ON COLUMN branches.address IS 'Branch address';
COMMENT ON COLUMN branches.city IS 'City';
COMMENT ON COLUMN branches.state IS 'State/Province';
COMMENT ON COLUMN branches.postal_code IS 'Postal/ZIP code';
COMMENT ON COLUMN branches.country IS 'Country';
COMMENT ON COLUMN branches.phone_number IS 'Phone number';
COMMENT ON COLUMN branches.email IS 'Email address';
COMMENT ON COLUMN branches.website_url IS 'Website URL';
COMMENT ON COLUMN branches.is_active IS 'Active status flag';
COMMENT ON COLUMN branches.logo_url IS 'Branch logo URL';
COMMENT ON COLUMN branches.settings_json IS 'Branch-specific settings in JSON format';
COMMENT ON COLUMN branches.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN branches.created_by IS 'User who created the record';
COMMENT ON COLUMN branches.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN branches.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN branches.deleted_at IS 'Soft delete timestamp';
