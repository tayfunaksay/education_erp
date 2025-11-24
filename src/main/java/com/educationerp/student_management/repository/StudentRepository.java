package com.educationerp.student_management.repository;

import com.educationerp.student_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity
 * Provides data access methods for student management
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find student by student number and active status
     */
    Optional<Student> findByStudentNumberAndIsActiveTrue(String studentNumber);

    /**
     * Find student by email and active status
     */
    Optional<Student> findByEmailAndIsActiveTrue(String email);

    /**
     * Find students by institution ID
     */
    List<Student> findByInstitutionIdAndIsActiveTrue(Long institutionId);

    /**
     * Find students by institution ID with pagination
     */
    Page<Student> findByInstitutionIdAndIsActiveTrue(Long institutionId, Pageable pageable);

    /**
     * Find students by branch ID
     */
    List<Student> findByBranchIdAndIsActiveTrue(Long branchId);

    /**
     * Find students by branch ID with pagination
     */
    Page<Student> findByBranchIdAndIsActiveTrue(Long branchId, Pageable pageable);

    /**
     * Find students by enrollment status
     */
    List<Student> findByEnrollmentStatusAndIsActiveTrue(Student.EnrollmentStatus enrollmentStatus);

    /**
     * Find students by enrollment status with pagination
     */
    Page<Student> findByEnrollmentStatusAndIsActiveTrue(Student.EnrollmentStatus enrollmentStatus, Pageable pageable);

    /**
     * Find students by grade level
     */
    @Query("SELECT s FROM Student s WHERE s.gradeLevel = :gradeLevel AND s.isActive = true")
    List<Student> findByGradeLevel(@Param("gradeLevel") String gradeLevel);

    /**
     * Find students by program
     */
    @Query("SELECT s FROM Student s WHERE s.program = :program AND s.isActive = true")
    List<Student> findByProgram(@Param("program") String program);

    /**
     * Find students by major
     */
    @Query("SELECT s FROM Student s WHERE s.major = :major AND s.isActive = true")
    List<Student> findByMajor(@Param("major") String major);

    /**
     * Find students by gender
     */
    List<Student> findByGenderAndIsActiveTrue(Student.Gender gender);

    /**
     * Find students by city
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.city) = LOWER(:city) AND s.isActive = true")
    List<Student> findByCityIgnoreCase(@Param("city") String city);

    /**
     * Find students by state
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.state) = LOWER(:state) AND s.isActive = true")
    List<Student> findByStateIgnoreCase(@Param("state") String state);

    /**
     * Find students by country
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.country) = LOWER(:country) AND s.isActive = true")
    List<Student> findByCountryIgnoreCase(@Param("country") String country);

    /**
     * Find students by name pattern
     */
    @Query("SELECT s FROM Student s WHERE (LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))) AND s.isActive = true")
    List<Student> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find students by name pattern with pagination
     */
    @Query("SELECT s FROM Student s WHERE (LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))) AND s.isActive = true")
    Page<Student> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Find students by enrollment date range
     */
    @Query("SELECT s FROM Student s WHERE s.enrollmentDate BETWEEN :startDate AND :endDate AND s.isActive = true")
    List<Student> findByEnrollmentDateBetween(@Param("startDate") LocalDate startDate, 
                                             @Param("endDate") LocalDate endDate);

    /**
     * Find students by date of birth range
     */
    @Query("SELECT s FROM Student s WHERE s.dateOfBirth BETWEEN :startDate AND :endDate AND s.isActive = true")
    List<Student> findByDateOfBirthBetween(@Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);

    /**
     * Find students with GPA above threshold
     */
    @Query("SELECT s FROM Student s WHERE s.gpa >= :gpa AND s.isActive = true")
    List<Student> findByGpaGreaterThanEqual(@Param("gpa") Double gpa);

    /**
     * Find students with GPA below threshold
     */
    @Query("SELECT s FROM Student s WHERE s.gpa < :gpa AND s.isActive = true")
    List<Student> findByGpaLessThan(@Param("gpa") Double gpa);

    /**
     * Find students by institution and enrollment status
     */
    @Query("SELECT s FROM Student s WHERE s.institutionId = :institutionId AND s.enrollmentStatus = :status AND s.isActive = true")
    List<Student> findByInstitutionIdAndEnrollmentStatus(@Param("institutionId") Long institutionId, 
                                                        @Param("status") Student.EnrollmentStatus status);

    /**
     * Find students by institution and grade level
     */
    @Query("SELECT s FROM Student s WHERE s.institutionId = :institutionId AND s.gradeLevel = :gradeLevel AND s.isActive = true")
    List<Student> findByInstitutionIdAndGradeLevel(@Param("institutionId") Long institutionId, 
                                                  @Param("gradeLevel") String gradeLevel);

    /**
     * Find students by institution and program
     */
    @Query("SELECT s FROM Student s WHERE s.institutionId = :institutionId AND s.program = :program AND s.isActive = true")
    List<Student> findByInstitutionIdAndProgram(@Param("institutionId") Long institutionId, 
                                               @Param("program") String program);

    /**
     * Check if student number exists within institution
     */
    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.institutionId = :institutionId AND s.studentNumber = :studentNumber")
    boolean existsByInstitutionIdAndStudentNumber(@Param("institutionId") Long institutionId, 
                                                 @Param("studentNumber") String studentNumber);

    /**
     * Check if student number exists within institution excluding specific student ID
     */
    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.institutionId = :institutionId AND s.studentNumber = :studentNumber AND s.id != :studentId")
    boolean existsByInstitutionIdAndStudentNumberAndIdNot(@Param("institutionId") Long institutionId, 
                                                         @Param("studentNumber") String studentNumber, 
                                                         @Param("studentId") Long studentId);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if email exists excluding specific student ID
     */
    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.email = :email AND s.id != :studentId")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("studentId") Long studentId);

    /**
     * Count students by institution
     */
    long countByInstitutionIdAndIsActiveTrue(Long institutionId);

    /**
     * Count students by branch
     */
    long countByBranchIdAndIsActiveTrue(Long branchId);

    /**
     * Count students by enrollment status
     */
    long countByEnrollmentStatusAndIsActiveTrue(Student.EnrollmentStatus enrollmentStatus);

    /**
     * Count students by grade level
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE s.gradeLevel = :gradeLevel AND s.isActive = true")
    long countByGradeLevel(@Param("gradeLevel") String gradeLevel);

    /**
     * Count students by program
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE s.program = :program AND s.isActive = true")
    long countByProgram(@Param("program") String program);

    /**
     * Count students by major
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE s.major = :major AND s.isActive = true")
    long countByMajor(@Param("major") String major);

    /**
     * Count students by gender
     */
    long countByGenderAndIsActiveTrue(Student.Gender gender);

    /**
     * Count students by city
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE LOWER(s.city) = LOWER(:city) AND s.isActive = true")
    long countByCityIgnoreCase(@Param("city") String city);

    /**
     * Count students by state
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE LOWER(s.state) = LOWER(:state) AND s.isActive = true")
    long countByStateIgnoreCase(@Param("state") String state);

    /**
     * Count students by country
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE LOWER(s.country) = LOWER(:country) AND s.isActive = true")
    long countByCountryIgnoreCase(@Param("country") String country);
}
