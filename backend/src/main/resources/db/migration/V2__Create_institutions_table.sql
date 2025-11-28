-- Create institutions table
CREATE TABLE institutions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    code VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(500),
    address VARCHAR(500) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100) UNIQUE,
    website_url VARCHAR(200),
    tenant_type VARCHAR(20) NOT NULL CHECK (tenant_type IN ('SHARED_SCHEMA', 'DEDICATED_DB', 'ON_PREMISE')),
    database_name VARCHAR(100),
    schema_name VARCHAR(100),
    connection_string VARCHAR(500),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    logo_url VARCHAR(500),
    settings_json VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    deleted_at TIMESTAMP
);

-- Create indexes for institutions table
CREATE INDEX idx_institutions_code ON institutions(code);
CREATE INDEX idx_institutions_email ON institutions(email);
CREATE INDEX idx_institutions_tenant_type ON institutions(tenant_type);
CREATE INDEX idx_institutions_city ON institutions(city);
CREATE INDEX idx_institutions_state ON institutions(state);
CREATE INDEX idx_institutions_country ON institutions(country);
CREATE INDEX idx_institutions_is_active ON institutions(is_active);
CREATE INDEX idx_institutions_created_date ON institutions(created_date);

-- Create composite indexes
CREATE INDEX idx_institutions_tenant_active ON institutions(tenant_type, is_active);
CREATE INDEX idx_institutions_city_active ON institutions(city, is_active);
CREATE INDEX idx_institutions_state_active ON institutions(state, is_active);
CREATE INDEX idx_institutions_country_active ON institutions(country, is_active);

-- Add comments for documentation
COMMENT ON TABLE institutions IS 'Educational institutions in the multi-tenant system';
COMMENT ON COLUMN institutions.id IS 'Primary key';
COMMENT ON COLUMN institutions.name IS 'Institution name';
COMMENT ON COLUMN institutions.code IS 'Unique institution code';
COMMENT ON COLUMN institutions.description IS 'Institution description';
COMMENT ON COLUMN institutions.address IS 'Institution address';
COMMENT ON COLUMN institutions.city IS 'City';
COMMENT ON COLUMN institutions.state IS 'State/Province';
COMMENT ON COLUMN institutions.postal_code IS 'Postal/ZIP code';
COMMENT ON COLUMN institutions.country IS 'Country';
COMMENT ON COLUMN institutions.phone_number IS 'Phone number';
COMMENT ON COLUMN institutions.email IS 'Email address';
COMMENT ON COLUMN institutions.website_url IS 'Website URL';
COMMENT ON COLUMN institutions.tenant_type IS 'Multi-tenant deployment type';
COMMENT ON COLUMN institutions.database_name IS 'Database name for dedicated DB type';
COMMENT ON COLUMN institutions.schema_name IS 'Schema name for shared schema type';
COMMENT ON COLUMN institutions.connection_string IS 'Connection string for on-premise type';
COMMENT ON COLUMN institutions.is_active IS 'Active status flag';
COMMENT ON COLUMN institutions.logo_url IS 'Institution logo URL';
COMMENT ON COLUMN institutions.settings_json IS 'Institution-specific settings in JSON format';
COMMENT ON COLUMN institutions.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN institutions.created_by IS 'User who created the record';
COMMENT ON COLUMN institutions.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN institutions.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN institutions.deleted_at IS 'Soft delete timestamp';
