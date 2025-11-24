-- Create educational_products table
CREATE TABLE educational_products (
    id BIGSERIAL PRIMARY KEY,
    institution_id BIGINT NOT NULL,
    branch_id BIGINT,
    product_code VARCHAR(50) NOT NULL,
    product_name VARCHAR(200) NOT NULL,
    isbn VARCHAR(20),
    publisher VARCHAR(100),
    author VARCHAR(100),
    grade_level VARCHAR(50),
    subject VARCHAR(100),
    product_type VARCHAR(20) NOT NULL DEFAULT 'BOOK' CHECK (product_type IN ('BOOK', 'WORKBOOK', 'REFERENCE', 'DICTIONARY', 'ATLAS', 'SOFTWARE', 'HARDWARE', 'EQUIPMENT', 'SUPPLIES', 'OTHER')),
    category VARCHAR(20) NOT NULL DEFAULT 'TEXTBOOK' CHECK (category IN ('TEXTBOOK', 'REFERENCE_BOOK', 'WORKBOOK', 'DICTIONARY', 'ATLAS', 'SOFTWARE', 'HARDWARE', 'EQUIPMENT', 'SUPPLIES', 'MOCK_EXAM', 'PRACTICE_TEST', 'STUDY_GUIDE', 'LITERATURE', 'SCIENCE_KIT', 'MATH_TOOLS', 'OTHER')),
    description VARCHAR(1000),
    purchase_price DECIMAL(10,2),
    selling_price DECIMAL(10,2),
    rental_price DECIMAL(10,2),
    current_stock INTEGER NOT NULL DEFAULT 0,
    minimum_stock INTEGER NOT NULL DEFAULT 0,
    maximum_stock INTEGER,
    reorder_point INTEGER NOT NULL DEFAULT 0,
    reorder_quantity INTEGER,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'DISCONTINUED', 'OUT_OF_STOCK')),
    is_rental_available BOOLEAN NOT NULL DEFAULT FALSE,
    is_mandatory BOOLEAN NOT NULL DEFAULT FALSE,
    edition VARCHAR(50),
    publication_year INTEGER,
    language VARCHAR(100),
    image_url VARCHAR(500),
    notes VARCHAR(1000),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100),
    updated_date TIMESTAMP,
    updated_by VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    deleted_at TIMESTAMP,
    
    CONSTRAINT fk_educational_products_institution FOREIGN KEY (institution_id) REFERENCES institutions(id),
    CONSTRAINT fk_educational_products_branch FOREIGN KEY (branch_id) REFERENCES branches(id),
    CONSTRAINT uk_educational_products_institution_code UNIQUE (institution_id, product_code),
    CONSTRAINT uk_educational_products_institution_isbn UNIQUE (institution_id, isbn)
);

-- Create indexes for educational_products table
CREATE INDEX idx_educational_products_institution_id ON educational_products(institution_id);
CREATE INDEX idx_educational_products_branch_id ON educational_products(branch_id);
CREATE INDEX idx_educational_products_product_code ON educational_products(product_code);
CREATE INDEX idx_educational_products_isbn ON educational_products(isbn);
CREATE INDEX idx_educational_products_publisher ON educational_products(publisher);
CREATE INDEX idx_educational_products_author ON educational_products(author);
CREATE INDEX idx_educational_products_grade_level ON educational_products(grade_level);
CREATE INDEX idx_educational_products_subject ON educational_products(subject);
CREATE INDEX idx_educational_products_product_type ON educational_products(product_type);
CREATE INDEX idx_educational_products_category ON educational_products(category);
CREATE INDEX idx_educational_products_status ON educational_products(status);
CREATE INDEX idx_educational_products_is_rental_available ON educational_products(is_rental_available);
CREATE INDEX idx_educational_products_is_mandatory ON educational_products(is_mandatory);
CREATE INDEX idx_educational_products_current_stock ON educational_products(current_stock);
CREATE INDEX idx_educational_products_reorder_point ON educational_products(reorder_point);
CREATE INDEX idx_educational_products_publication_year ON educational_products(publication_year);
CREATE INDEX idx_educational_products_language ON educational_products(language);
CREATE INDEX idx_educational_products_is_active ON educational_products(is_active);
CREATE INDEX idx_educational_products_created_date ON educational_products(created_date);

-- Create composite indexes
CREATE INDEX idx_educational_products_institution_active ON educational_products(institution_id, is_active);
CREATE INDEX idx_educational_products_branch_active ON educational_products(branch_id, is_active);
CREATE INDEX idx_educational_products_institution_type ON educational_products(institution_id, product_type);
CREATE INDEX idx_educational_products_institution_category ON educational_products(institution_id, category);
CREATE INDEX idx_educational_products_institution_grade ON educational_products(institution_id, grade_level);
CREATE INDEX idx_educational_products_institution_subject ON educational_products(institution_id, subject);
CREATE INDEX idx_educational_products_institution_status ON educational_products(institution_id, status);

-- Add comments for documentation
COMMENT ON TABLE educational_products IS 'Educational products and resources (books, materials, etc.)';
COMMENT ON COLUMN educational_products.id IS 'Primary key';
COMMENT ON COLUMN educational_products.institution_id IS 'Reference to institution';
COMMENT ON COLUMN educational_products.branch_id IS 'Reference to branch';
COMMENT ON COLUMN educational_products.product_code IS 'Product code (unique within institution)';
COMMENT ON COLUMN educational_products.product_name IS 'Product name';
COMMENT ON COLUMN educational_products.isbn IS 'ISBN number (unique within institution)';
COMMENT ON COLUMN educational_products.publisher IS 'Publisher name';
COMMENT ON COLUMN educational_products.author IS 'Author name';
COMMENT ON COLUMN educational_products.grade_level IS 'Target grade level';
COMMENT ON COLUMN educational_products.subject IS 'Subject area';
COMMENT ON COLUMN educational_products.product_type IS 'Type of product';
COMMENT ON COLUMN educational_products.category IS 'Product category';
COMMENT ON COLUMN educational_products.description IS 'Product description';
COMMENT ON COLUMN educational_products.purchase_price IS 'Purchase price';
COMMENT ON COLUMN educational_products.selling_price IS 'Selling price';
COMMENT ON COLUMN educational_products.rental_price IS 'Rental price';
COMMENT ON COLUMN educational_products.current_stock IS 'Current stock quantity';
COMMENT ON COLUMN educational_products.minimum_stock IS 'Minimum stock level';
COMMENT ON COLUMN educational_products.maximum_stock IS 'Maximum stock level';
COMMENT ON COLUMN educational_products.reorder_point IS 'Reorder point';
COMMENT ON COLUMN educational_products.reorder_quantity IS 'Reorder quantity';
COMMENT ON COLUMN educational_products.status IS 'Product status';
COMMENT ON COLUMN educational_products.is_rental_available IS 'Rental availability flag';
COMMENT ON COLUMN educational_products.is_mandatory IS 'Mandatory product flag';
COMMENT ON COLUMN educational_products.edition IS 'Edition information';
COMMENT ON COLUMN educational_products.publication_year IS 'Publication year';
COMMENT ON COLUMN educational_products.language IS 'Language';
COMMENT ON COLUMN educational_products.image_url IS 'Product image URL';
COMMENT ON COLUMN educational_products.notes IS 'Additional notes';
COMMENT ON COLUMN educational_products.created_date IS 'Record creation timestamp';
COMMENT ON COLUMN educational_products.created_by IS 'User who created the record';
COMMENT ON COLUMN educational_products.updated_date IS 'Record last update timestamp';
COMMENT ON COLUMN educational_products.updated_by IS 'User who last updated the record';
COMMENT ON COLUMN educational_products.is_active IS 'Active status flag';
COMMENT ON COLUMN educational_products.deleted_at IS 'Soft delete timestamp';
