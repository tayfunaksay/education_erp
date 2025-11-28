package com.educationerp.core.config;

import com.educationerp.user_management.entity.User;
import com.educationerp.user_management.repository.UserRepository;
import com.educationerp.security.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Data loader for initializing test users
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if admin user already exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@educationerp.com");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(Role.SUPER_ADMIN);
            admin.setIsActive(true);
            admin.setIsLocked(false);
            admin.setMustChangePassword(false);
            admin.setFailedLoginAttempts(0);
            
            userRepository.save(admin);
            logger.info("✅ Test admin user created - Username: admin, Password: admin123");
        }

        // Create a test teacher user
        if (userRepository.findByUsername("teacher").isEmpty()) {
            User teacher = new User();
            teacher.setUsername("teacher");
            teacher.setPassword(passwordEncoder.encode("teacher123"));
            teacher.setEmail("teacher@educationerp.com");
            teacher.setFirstName("Test");
            teacher.setLastName("Teacher");
            teacher.setRole(Role.TEACHER);
            teacher.setIsActive(true);
            teacher.setIsLocked(false);
            teacher.setMustChangePassword(false);
            teacher.setFailedLoginAttempts(0);
            
            userRepository.save(teacher);
            logger.info("✅ Test teacher user created - Username: teacher, Password: teacher123");
        }

        // Create a test student user
        if (userRepository.findByUsername("student").isEmpty()) {
            User student = new User();
            student.setUsername("student");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setEmail("student@educationerp.com");
            student.setFirstName("Test");
            student.setLastName("Student");
            student.setRole(Role.STUDENT);
            student.setIsActive(true);
            student.setIsLocked(false);
            student.setMustChangePassword(false);
            student.setFailedLoginAttempts(0);
            
            userRepository.save(student);
            logger.info("✅ Test student user created - Username: student, Password: student123");
        }

        logger.info("🎉 Test data initialization completed!");
    }
}
