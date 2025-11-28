package com.educationerp.institution_management.controller;

import com.educationerp.core.dto.ApiResponse;
import com.educationerp.institution_management.dto.CreateInstitutionRequest;
import com.educationerp.institution_management.dto.InstitutionResponse;
import com.educationerp.institution_management.service.InstitutionService;
import com.educationerp.security.enums.TenantType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Institution management operations
 * Provides CRUD operations for institutions with role-based access control
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/institutions")
@Tag(name = "Institution Management", description = "Institution management operations")
public class InstitutionController {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);

    @Autowired
    private InstitutionService institutionService;

    /**
     * Create a new institution
     */
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Create institution", description = "Create a new institution in the system")
    public ResponseEntity<ApiResponse<InstitutionResponse>> createInstitution(@Valid @RequestBody CreateInstitutionRequest request) {
        logger.info("Creating new institution with code: {}", request.getCode());
        
        InstitutionResponse institutionResponse = institutionService.createInstitution(request);
        ApiResponse<InstitutionResponse> response = ApiResponse.success("Institution created successfully", institutionResponse);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get institution by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get institution by ID", description = "Retrieve institution information by ID")
    public ResponseEntity<ApiResponse<InstitutionResponse>> getInstitutionById(
            @Parameter(description = "Institution ID") @PathVariable Long id) {
        
        InstitutionResponse institutionResponse = institutionService.getInstitutionById(id);
        ApiResponse<InstitutionResponse> response = ApiResponse.success("Institution retrieved successfully", institutionResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get institution by code
     */
    @GetMapping("/code/{code}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get institution by code", description = "Retrieve institution information by code")
    public ResponseEntity<ApiResponse<InstitutionResponse>> getInstitutionByCode(
            @Parameter(description = "Institution code") @PathVariable String code) {
        
        InstitutionResponse institutionResponse = institutionService.getInstitutionByCode(code);
        ApiResponse<InstitutionResponse> response = ApiResponse.success("Institution retrieved successfully", institutionResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get all institutions with pagination
     */
    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get all institutions", description = "Retrieve all institutions with pagination")
    public ResponseEntity<ApiResponse<Page<InstitutionResponse>>> getAllInstitutions(Pageable pageable) {
        Page<InstitutionResponse> institutions = institutionService.getAllInstitutions(pageable);
        ApiResponse<Page<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get institutions by tenant type
     */
    @GetMapping("/tenant-type/{tenantType}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get institutions by tenant type", description = "Retrieve institutions filtered by tenant type")
    public ResponseEntity<ApiResponse<List<InstitutionResponse>>> getInstitutionsByTenantType(
            @Parameter(description = "Tenant type") @PathVariable TenantType tenantType) {
        
        List<InstitutionResponse> institutions = institutionService.getInstitutionsByTenantType(tenantType);
        ApiResponse<List<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get institutions by city
     */
    @GetMapping("/city/{city}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get institutions by city", description = "Retrieve institutions filtered by city")
    public ResponseEntity<ApiResponse<List<InstitutionResponse>>> getInstitutionsByCity(
            @Parameter(description = "City name") @PathVariable String city) {
        
        List<InstitutionResponse> institutions = institutionService.getInstitutionsByCity(city);
        ApiResponse<List<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get institutions by state
     */
    @GetMapping("/state/{state}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get institutions by state", description = "Retrieve institutions filtered by state")
    public ResponseEntity<ApiResponse<List<InstitutionResponse>>> getInstitutionsByState(
            @Parameter(description = "State name") @PathVariable String state) {
        
        List<InstitutionResponse> institutions = institutionService.getInstitutionsByState(state);
        ApiResponse<List<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Get institutions by country
     */
    @GetMapping("/country/{country}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Get institutions by country", description = "Retrieve institutions filtered by country")
    public ResponseEntity<ApiResponse<List<InstitutionResponse>>> getInstitutionsByCountry(
            @Parameter(description = "Country name") @PathVariable String country) {
        
        List<InstitutionResponse> institutions = institutionService.getInstitutionsByCountry(country);
        ApiResponse<List<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Search institutions by name
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Search institutions by name", description = "Search institutions by name pattern")
    public ResponseEntity<ApiResponse<List<InstitutionResponse>>> searchInstitutionsByName(
            @Parameter(description = "Name pattern") @RequestParam String name) {
        
        List<InstitutionResponse> institutions = institutionService.searchInstitutionsByName(name);
        ApiResponse<List<InstitutionResponse>> response = ApiResponse.success("Institutions retrieved successfully", institutions);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Update institution
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('INSTITUTION_ADMIN')")
    @Operation(summary = "Update institution", description = "Update institution information")
    public ResponseEntity<ApiResponse<InstitutionResponse>> updateInstitution(
            @Parameter(description = "Institution ID") @PathVariable Long id,
            @Valid @RequestBody CreateInstitutionRequest request) {
        
        logger.info("Updating institution with ID: {}", id);
        
        InstitutionResponse institutionResponse = institutionService.updateInstitution(id, request);
        ApiResponse<InstitutionResponse> response = ApiResponse.success("Institution updated successfully", institutionResponse);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Delete institution (soft delete)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Delete institution", description = "Soft delete institution")
    public ResponseEntity<ApiResponse<Void>> deleteInstitution(
            @Parameter(description = "Institution ID") @PathVariable Long id) {
        
        logger.info("Deleting institution with ID: {}", id);
        
        institutionService.deleteInstitution(id);
        ApiResponse<Void> response = ApiResponse.success("Institution deleted successfully", null);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Activate institution
     */
    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Activate institution", description = "Activate institution")
    public ResponseEntity<ApiResponse<Void>> activateInstitution(
            @Parameter(description = "Institution ID") @PathVariable Long id) {
        
        logger.info("Activating institution with ID: {}", id);
        
        institutionService.activateInstitution(id);
        ApiResponse<Void> response = ApiResponse.success("Institution activated successfully", null);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Deactivate institution
     */
    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Deactivate institution", description = "Deactivate institution")
    public ResponseEntity<ApiResponse<Void>> deactivateInstitution(
            @Parameter(description = "Institution ID") @PathVariable Long id) {
        
        logger.info("Deactivating institution with ID: {}", id);
        
        institutionService.deactivateInstitution(id);
        ApiResponse<Void> response = ApiResponse.success("Institution deactivated successfully", null);
        
        return ResponseEntity.ok(response);
    }
}
