# Education ERP System

A comprehensive multi-tenant education ERP system built with Spring Boot 3.2.x, designed to manage educational institutions, students, courses, payments, and more.

## 🚀 Features

### Core Modules
- **User Management**: Role-based access control with 9 different roles
- **Institution Management**: Multi-tenant support with branch management
- **Student Management**: Complete student lifecycle management
- **Course Management**: Course and curriculum management
- **Payment Management**: Payment processing with installment tracking
- **Inventory Management**: Educational resource and book management
- **Notification System**: SMS, Email, and Push notifications
- **Reporting System**: Comprehensive reporting and analytics

### Technical Features
- **Multi-tenant Architecture**: Support for SHARED_SCHEMA, DEDICATED_DB, and ON-PREMISE deployments
- **JWT Authentication**: Secure token-based authentication with Redis blacklist
- **Database Migration**: Flyway-based database versioning
- **Comprehensive Testing**: Unit, integration, and performance tests
- **Docker Support**: Containerized deployment
- **API Documentation**: OpenAPI 3 + Swagger UI
- **Monitoring**: Spring Boot Actuator with health checks

## 🛠️ Technology Stack

- **Java**: 17 LTS
- **Spring Boot**: 3.2.x
- **Database**: PostgreSQL with HikariCP
- **Migration**: Flyway
- **Security**: JWT with JJWT
- **Caching**: Caffeine
- **Testing**: JUnit 5, Mockito, TestContainers
- **Documentation**: OpenAPI 3 + Swagger UI
- **Build Tool**: Maven
- **Containerization**: Docker

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 13+
- Docker (optional)
- Redis (for JWT blacklist)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd education-erp
```

### 2. Database Setup
```bash
# Create PostgreSQL database
createdb education_erp

# Update application.yml with your database credentials
```

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Access the Application
- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Actuator Health**: http://localhost:8080/actuator/health

## 🐳 Docker Deployment

### Build Docker Image
```bash
docker build -t education-erp:latest .
```

### Run with Docker Compose
```bash
docker-compose up -d
```

## 📊 Database Schema

The system uses Flyway for database migrations. Key tables include:

- `institutions` - Educational institutions
- `branches` - Institution branches
- `users` - System users with roles
- `students` - Student records
- `courses` - Course information
- `educational_products` - Books and resources
- `payments` - Payment transactions
- `notifications` - Notification records
- `reports` - Generated reports

## 🔐 Security

### Authentication
- JWT-based authentication
- Token refresh mechanism
- Redis-based token blacklist
- Password policies and validation

### Authorization
- Role-based access control (RBAC)
- 9 predefined roles with fine-grained permissions
- Multi-tenant data isolation

### Roles
1. **SUPER_ADMIN** - System-wide administration
2. **INSTITUTION_ADMIN** - Institution management
3. **BRANCH_MANAGER** - Branch management
4. **COURSE_MANAGER** - Course management
5. **ACCOUNTANT** - Financial operations
6. **REPORT_VIEWER** - Report access
7. **TEACHER** - Teaching operations
8. **STUDENT** - Student access
9. **PARENT** - Parent access

## 🧪 Testing

### Run Tests
```bash
# Run all tests
mvn test

# Run specific test profile
mvn test -Dspring.profiles.active=test-unit

# Run integration tests
mvn test -Dspring.profiles.active=test-integration

# Run performance tests
mvn test -Dspring.profiles.active=test-performance
```

### Test Coverage
- **Unit Tests**: 80%+ coverage
- **Integration Tests**: Full API testing
- **Performance Tests**: Load and stress testing

## 📈 Monitoring

### Health Checks
- Database connectivity
- Redis connectivity
- External service status
- Tenant-specific health monitoring

### Metrics
- Application performance metrics
- Business metrics (student count, revenue, etc.)
- System resource usage

## 🔧 Configuration

### Application Properties
Key configuration options in `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/education_erp
    username: your_username
    password: your_password
  
  security:
    jwt:
      secret: your-jwt-secret
      expiration: 86400000  # 24 hours
```

### Multi-tenant Configuration
```yaml
education-erp:
  multi-tenant:
    default-tenant-type: SHARED_SCHEMA
    tenant-resolution-strategy: HEADER
```

## 📚 API Documentation

### Swagger UI
Access the interactive API documentation at:
http://localhost:8080/swagger-ui.html

### Key Endpoints
- `POST /api/auth/login` - User authentication
- `GET /api/students` - List students
- `POST /api/students` - Create student
- `GET /api/courses` - List courses
- `POST /api/payments` - Process payment
- `GET /api/reports` - Generate reports

## 🚀 Deployment

### Production Deployment
1. **Database Setup**: Configure PostgreSQL with proper security
2. **Redis Setup**: Configure Redis for JWT blacklist
3. **Environment Variables**: Set production configuration
4. **SSL/TLS**: Configure HTTPS
5. **Load Balancer**: Set up load balancing for high availability

### Environment Variables
```bash
export SPRING_PROFILES_ACTIVE=production
export DATABASE_URL=jdbc:postgresql://your-db-host:5432/education_erp
export REDIS_URL=redis://your-redis-host:6379
export JWT_SECRET=your-production-jwt-secret
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the API documentation

## 🔮 Future Enhancements

- **e-Fatura Integration**: Turkish e-invoice system
- **e-Arşiv Integration**: Turkish e-archive system
- **e-Defter Integration**: Turkish e-ledger system
- **Mobile App**: React Native mobile application
- **Advanced Analytics**: Machine learning insights
- **Multi-language Support**: Internationalization
- **Advanced Reporting**: Custom report builder

---

**Education ERP System** - Empowering educational institutions with modern technology.