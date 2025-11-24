package com.educationerp.institution_management.service;

import com.educationerp.core.exception.BusinessException;
import com.educationerp.core.exception.ResourceNotFoundException;
import com.educationerp.institution_management.dto.CreateInstitutionRequest;
import com.educationerp.institution_management.dto.InstitutionResponse;
import com.educationerp.institution_management.entity.Institution;
import com.educationerp.institution_management.repository.InstitutionRepository;
import com.educationerp.security.enums.TenantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Institution management operations
 * Handles business logic for institution CRUD operations
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Service
@Transactional
public class InstitutionService {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionService.class);

    @Autowired
    private InstitutionRepository institutionRepository;

    /**
     * Create a new institution
     */
    public InstitutionResponse createInstitution(CreateInstitutionRequest request) {
        logger.info("Creating new institution with code: {}", request.getCode());

        // Validate unique constraints
        if (institutionRepository.existsByCode(request.getCode())) {
            throw new BusinessException("Institution code already exists: " + request.getCode());
        }

        if (request.getEmail() != null && institutionRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Institution email already exists: " + request.getEmail());
        }

        // Create institution entity
        Institution institution = new Institution();
        institution.setName(request.getName());
        institution.setCode(request.getCode());
        institution.setDescription(request.getDescription());
        institution.setAddress(request.getAddress());
        institution.setCity(request.getCity());
        institution.setState(request.getState());
        institution.setPostalCode(request.getPostalCode());
        institution.setCountry(request.getCountry());
        institution.setPhoneNumber(request.getPhoneNumber());
        institution.setEmail(request.getEmail());
        institution.setWebsiteUrl(request.getWebsiteUrl());
        institution.setTenantType(request.getTenantType());
        institution.setDatabaseName(request.getDatabaseName());
        institution.setSchemaName(request.getSchemaName());
        institution.setConnectionString(request.getConnectionString());
        institution.setLogoUrl(request.getLogoUrl());
        institution.setSettingsJson(request.getSettingsJson());

        Institution savedInstitution = institutionRepository.save(institution);
        logger.info("Institution created successfully with ID: {}", savedInstitution.getId());

        return mapToInstitutionResponse(savedInstitution);
    }

    /**
     * Get institution by ID
     */
    @Transactional(readOnly = true)
    public InstitutionResponse getInstitutionById(Long id) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));

        return mapToInstitutionResponse(institution);
    }

    /**
     * Get institution by code
     */
    @Transactional(readOnly = true)
    public InstitutionResponse getInstitutionByCode(String code) {
        Institution institution = institutionRepository.findByCodeAndIsActiveTrue(code)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with code: " + code));

        return mapToInstitutionResponse(institution);
    }

    /**
     * Get all institutions with pagination
     */
    @Transactional(readOnly = true)
    public Page<InstitutionResponse> getAllInstitutions(Pageable pageable) {
        Page<Institution> institutions = institutionRepository.findAll(pageable);
        return institutions.map(this::mapToInstitutionResponse);
    }

    /**
     * Get institutions by tenant type
     */
    @Transactional(readOnly = true)
    public List<InstitutionResponse> getInstitutionsByTenantType(TenantType tenantType) {
        List<Institution> institutions = institutionRepository.findByTenantTypeAndIsActiveTrue(tenantType);
        return institutions.stream()
                .map(this::mapToInstitutionResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get institutions by city
     */
    @Transactional(readOnly = true)
    public List<InstitutionResponse> getInstitutionsByCity(String city) {
        List<Institution> institutions = institutionRepository.findByCityIgnoreCase(city);
        return institutions.stream()
                .map(this::mapToInstitutionResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get institutions by state
     */
    @Transactional(readOnly = true)
    public List<InstitutionResponse> getInstitutionsByState(String state) {
        List<Institution> institutions = institutionRepository.findByStateIgnoreCase(state);
        return institutions.stream()
                .map(this::mapToInstitutionResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get institutions by country
     */
    @Transactional(readOnly = true)
    public List<InstitutionResponse> getInstitutionsByCountry(String country) {
        List<Institution> institutions = institutionRepository.findByCountryIgnoreCase(country);
        return institutions.stream()
                .map(this::mapToInstitutionResponse)
                .collect(Collectors.toList());
    }

    /**
     * Search institutions by name
     */
    @Transactional(readOnly = true)
    public List<InstitutionResponse> searchInstitutionsByName(String name) {
        List<Institution> institutions = institutionRepository.findByNameContainingIgnoreCase(name);
        return institutions.stream()
                .map(this::mapToInstitutionResponse)
                .collect(Collectors.toList());
    }

    /**
     * Update institution
     */
    public InstitutionResponse updateInstitution(Long id, CreateInstitutionRequest request) {
        logger.info("Updating institution with ID: {}", id);

        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));

        // Validate unique constraints for updated values
        if (!institution.getCode().equals(request.getCode()) && 
            institutionRepository.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new BusinessException("Institution code already exists: " + request.getCode());
        }

        if (request.getEmail() != null && !institution.getEmail().equals(request.getEmail()) && 
            institutionRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BusinessException("Institution email already exists: " + request.getEmail());
        }

        // Update institution fields
        institution.setName(request.getName());
        institution.setCode(request.getCode());
        institution.setDescription(request.getDescription());
        institution.setAddress(request.getAddress());
        institution.setCity(request.getCity());
        institution.setState(request.getState());
        institution.setPostalCode(request.getPostalCode());
        institution.setCountry(request.getCountry());
        institution.setPhoneNumber(request.getPhoneNumber());
        institution.setEmail(request.getEmail());
        institution.setWebsiteUrl(request.getWebsiteUrl());
        institution.setTenantType(request.getTenantType());
        institution.setDatabaseName(request.getDatabaseName());
        institution.setSchemaName(request.getSchemaName());
        institution.setConnectionString(request.getConnectionString());
        institution.setLogoUrl(request.getLogoUrl());
        institution.setSettingsJson(request.getSettingsJson());

        Institution updatedInstitution = institutionRepository.save(institution);
        logger.info("Institution updated successfully with ID: {}", updatedInstitution.getId());

        return mapToInstitutionResponse(updatedInstitution);
    }

    /**
     * Soft delete institution
     */
    public void deleteInstitution(Long id) {
        logger.info("Deleting institution with ID: {}", id);

        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));

        institution.softDelete();
        institutionRepository.save(institution);

        logger.info("Institution deleted successfully with ID: {}", id);
    }

    /**
     * Activate institution
     */
    public void activateInstitution(Long id) {
        logger.info("Activating institution with ID: {}", id);

        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));

        institution.setIsActive(true);
        institutionRepository.save(institution);

        logger.info("Institution activated successfully with ID: {}", id);
    }

    /**
     * Deactivate institution
     */
    public void deactivateInstitution(Long id) {
        logger.info("Deactivating institution with ID: {}", id);

        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found with ID: " + id));

        institution.setIsActive(false);
        institutionRepository.save(institution);

        logger.info("Institution deactivated successfully with ID: {}", id);
    }

    /**
     * Map Institution entity to InstitutionResponse DTO
     */
    private InstitutionResponse mapToInstitutionResponse(Institution institution) {
        return InstitutionResponse.builder()
                .id(institution.getId())
                .name(institution.getName())
                .code(institution.getCode())
                .description(institution.getDescription())
                .address(institution.getAddress())
                .city(institution.getCity())
                .state(institution.getState())
                .postalCode(institution.getPostalCode())
                .country(institution.getCountry())
                .fullAddress(institution.getFullAddress())
                .phoneNumber(institution.getPhoneNumber())
                .email(institution.getEmail())
                .websiteUrl(institution.getWebsiteUrl())
                .tenantType(institution.getTenantType())
                .databaseName(institution.getDatabaseName())
                .schemaName(institution.getSchemaName())
                .connectionString(institution.getConnectionString())
                .isActive(institution.getIsActive())
                .logoUrl(institution.getLogoUrl())
                .settingsJson(institution.getSettingsJson())
                .createdDate(institution.getCreatedDate())
                .createdBy(institution.getCreatedBy())
                .updatedDate(institution.getUpdatedDate())
                .updatedBy(institution.getUpdatedBy())
                .build();
    }
}
