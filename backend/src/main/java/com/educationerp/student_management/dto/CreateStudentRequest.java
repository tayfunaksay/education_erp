package com.educationerp.student_management.dto;

import com.educationerp.student_management.entity.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for creating a new student
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public class CreateStudentRequest {

    @NotNull(message = "Institution ID is required")
    private Long institutionId;

    private Long branchId;

    @NotBlank(message = "Student number is required")
    @Size(max = 50, message = "Student number must not exceed 50 characters")
    private String studentNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    private String middleName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private Student.Gender gender;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    private Student.EnrollmentStatus enrollmentStatus = Student.EnrollmentStatus.ACTIVE;

    @Size(max = 50, message = "Grade level must not exceed 50 characters")
    private String gradeLevel;

    @Size(max = 100, message = "Program must not exceed 100 characters")
    private String program;

    @Size(max = 100, message = "Major must not exceed 100 characters")
    private String major;

    private Double gpa;

    @Size(max = 500, message = "Profile picture URL must not exceed 500 characters")
    private String profilePictureUrl;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;

    @Size(max = 100, message = "Emergency contact name must not exceed 100 characters")
    private String emergencyContactName;

    @Size(max = 20, message = "Emergency contact phone must not exceed 20 characters")
    private String emergencyContactPhone;

    @Size(max = 200, message = "Emergency contact relationship must not exceed 200 characters")
    private String emergencyContactRelationship;

    // Constructors
    public CreateStudentRequest() {
    }

    public CreateStudentRequest(Long institutionId, Long branchId, String studentNumber, String firstName, 
                               String lastName, String middleName, LocalDate dateOfBirth, Student.Gender gender, 
                               String phoneNumber, String email, String address, String city, String state, 
                               String postalCode, String country, LocalDate enrollmentDate, 
                               Student.EnrollmentStatus enrollmentStatus, String gradeLevel, String program, String major) {
        this.institutionId = institutionId;
        this.branchId = branchId;
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.enrollmentDate = enrollmentDate;
        this.enrollmentStatus = enrollmentStatus;
        this.gradeLevel = gradeLevel;
        this.program = program;
        this.major = major;
    }

    // Getters and Setters
    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Student.Gender getGender() {
        return gender;
    }

    public void setGender(Student.Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Student.EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(Student.EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateStudentRequest that = (CreateStudentRequest) o;
        return Objects.equals(institutionId, that.institutionId) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(studentNumber, that.studentNumber) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                gender == that.gender &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(enrollmentDate, that.enrollmentDate) &&
                enrollmentStatus == that.enrollmentStatus &&
                Objects.equals(gradeLevel, that.gradeLevel) &&
                Objects.equals(program, that.program) &&
                Objects.equals(major, that.major) &&
                Objects.equals(gpa, that.gpa) &&
                Objects.equals(profilePictureUrl, that.profilePictureUrl) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(emergencyContactName, that.emergencyContactName) &&
                Objects.equals(emergencyContactPhone, that.emergencyContactPhone) &&
                Objects.equals(emergencyContactRelationship, that.emergencyContactRelationship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId, branchId, studentNumber, firstName, lastName, middleName, 
                           dateOfBirth, gender, phoneNumber, email, address, city, state, postalCode, 
                           country, enrollmentDate, enrollmentStatus, gradeLevel, program, major, gpa, 
                           profilePictureUrl, notes, emergencyContactName, emergencyContactPhone, 
                           emergencyContactRelationship);
    }

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "institutionId=" + institutionId +
                ", branchId=" + branchId +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", enrollmentStatus=" + enrollmentStatus +
                '}';
    }
}
