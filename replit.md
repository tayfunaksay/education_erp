# Education ERP System - Replit Configuration

## Project Overview

This is a multi-tenant Education ERP System built with **Spring Boot 3.2.1** and **Java 17**. The system provides comprehensive management capabilities for educational institutions, including user management, student enrollment, course management, payments, inventory, notifications, and reporting.

## Technology Stack

- **Java**: 17 (GraalVM)
- **Spring Boot**: 3.2.1
- **Database**: PostgreSQL 16.9 (Neon-hosted, provided by Replit)
- **Build Tool**: Maven
- **ORM**: Hibernate with JPA
- **Database Migrations**: Flyway
- **Security**: JWT-based authentication with Spring Security
- **Caching**: Caffeine
- **API Documentation**: OpenAPI 3 + Swagger UI
- **Monitoring**: Spring Boot Actuator

## Project Structure

```
education-erp/
├── src/
│   └── main/
│       ├── java/com/educationerp/
│       │   ├── core/               # Core configurations and base classes
│       │   ├── security/            # JWT authentication and security
│       │   ├── user_management/     # User and role management
│       │   ├── institution_management/  # Institution and branch management
│       │   ├── student_management/  # Student and enrollment management
│       │   ├── course_management/   # Course and curriculum management
│       │   ├── payment_management/  # Payment and installment processing
│       │   ├── inventory/           # Educational products and distribution
│       │   ├── notification/        # Multi-channel notifications
│       │   └── reporting/           # Report generation and analytics
│       └── resources/
│           ├── db/migration/        # Flyway database migrations
│           └── application.properties  # Application configuration
├── pom.xml                          # Maven dependencies
└── README.md                        # Original project documentation
```

## Replit Environment Setup

### Database Configuration

The application uses Replit's built-in PostgreSQL database. The database connection is configured automatically using the following environment variables (provided by Replit):

- `PGHOST` - Database host
- `PGPORT` - Database port (5432)
- `PGUSER` - Database username
- `PGPASSWORD` - Database password
- `PGDATABASE` - Database name

### Configuration Changes for Replit

The following changes were made to run this project on Replit:

1. **Database URL Configuration** (`src/main/resources/application.properties`):
   - Modified to use Replit's PostgreSQL environment variables
   - Added `sslmode=require` for secure connections to Neon database

2. **Hibernate DDL Strategy**:
   - Changed from `validate` to `update` to allow Hibernate to create missing tables
   - Note: In production, proper Flyway migrations should be created for all entities

3. **Server Context Path**:
   - Changed from `/education-erp` to `/` for cleaner URLs

4. **Repository Methods**:
   - Removed invalid repository methods that referenced non-existent entity fields
   - Fixed `NotificationRepository` and `ReportRepository`

## Running the Application

The application is configured to run automatically in Replit. The workflow is set up to execute:

```bash
mvn spring-boot:run
```

The application will:
1. Build the project using Maven
2. Run Flyway migrations to set up the database schema
3. Create any missing tables using Hibernate
4. Start the Spring Boot application on port 8080

### Access Points

- **Application Base URL**: `http://localhost:8080`
- **API Endpoints**: `http://localhost:8080/api/...`
- **Actuator Health Check**: `http://localhost:8080/actuator/health`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html` (requires authentication)
- **API Docs**: `http://localhost:8080/api-docs` (requires authentication)

## Database Schema

The database schema includes the following main tables:

- **users** - System users with role-based access control
- **institutions** - Educational institutions
- **branches** - Institution branches
- **students** - Student records
- **courses** - Course information
- **curricula** - Curriculum definitions
- **enrollments** - Student course enrollments
- **educational_products** - Books and educational materials
- **product_packages** - Bundled product packages
- **student_product_distributions** - Product distribution tracking
- **payments** - Payment transactions
- **installment_plans** - Payment installment plans
- **notifications** - Multi-channel notifications
- **reports** - Generated reports

## Security

The application uses JWT-based authentication with Spring Security. Default configuration:

- **JWT Secret**: Configured in `application.properties`
- **Access Token Expiration**: 30 minutes (1800000 ms)
- **Refresh Token Expiration**: 24 hours (86400000 ms)

### User Roles

The system supports 9 different roles:
1. SUPER_ADMIN - System-wide administration
2. INSTITUTION_ADMIN - Institution management
3. BRANCH_MANAGER - Branch management
4. COURSE_MANAGER - Course management
5. ACCOUNTANT - Financial operations
6. REPORT_VIEWER - Report access
7. TEACHER - Teaching operations
8. STUDENT - Student access
9. PARENT - Parent access

## Development Notes

### Known Issues

1. **Mail Health Check**: The `/actuator/health` endpoint shows "DOWN" because email credentials are not configured. This doesn't affect the application's core functionality.

2. **Flyway Migrations**: Some entity tables are created by Hibernate (`update` mode) rather than Flyway migrations. For production deployment, create proper migration files for:
   - curricula
   - enrollments
   - product_packages
   - student_product_distributions
   - installment_plans

### TODO Items

- [ ] Create complete Flyway migrations for all entities
- [ ] Configure email service for notifications
- [ ] Set up proper JWT secret using Replit secrets
- [ ] Add integration tests
- [ ] Configure CORS for production deployments
- [ ] Set up Redis for JWT token blacklist (optional)

## Deployment

The application is configured for deployment using Replit's VM deployment target. The deployment will:
- Use the same `mvn spring-boot:run` command
- Maintain the database connection using Replit's environment variables
- Run continuously to handle requests

## Recent Changes (Replit Setup)

**Date**: November 27, 2025

**Changes Made**:
1. Configured PostgreSQL database using Replit's built-in database
2. Updated `application.properties` to use Replit environment variables
3. Changed Hibernate DDL strategy from `validate` to `update`
4. Fixed repository methods with non-existent entity field references
5. Set up Maven workflow to run Spring Boot application
6. Configured deployment for Replit VM

## Support

For issues or questions about the original project, refer to the `README.md` file.

For Replit-specific issues:
- Check workflow logs for errors
- Verify database connection using environment variables
- Ensure all dependencies are installed via Maven
