package com.educationerp.security.jwt;

import com.educationerp.security.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT utility class for token generation, validation, and extraction
 * Supports scenario-based token expiration for different user types
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret:education-erp-secret-key-that-should-be-at-least-256-bits-long}")
    private String secret;

    @Value("${jwt.access-token.expiration:1800000}") // 30 minutes default
    private Long accessTokenExpiration;

    @Value("${jwt.refresh-token.expiration:86400000}") // 24 hours default
    private Long refreshTokenExpiration;

    @Value("${jwt.readonly-access-token.expiration:86400000}") // 24 hours for read-only users
    private Long readonlyAccessTokenExpiration;

    @Value("${jwt.readonly-refresh-token.expiration:2592000000}") // 30 days for read-only users
    private Long readonlyRefreshTokenExpiration;

    /**
     * Generate access token for user
     */
    public String generateAccessToken(UserDetails userDetails, String tenantId, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", tenantId);
        claims.put("role", role.name());
        claims.put("tokenType", "access");
        
        return createToken(claims, userDetails.getUsername(), getAccessTokenExpiration(role));
    }

    /**
     * Generate refresh token for user
     */
    public String generateRefreshToken(UserDetails userDetails, String tenantId, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tenantId", tenantId);
        claims.put("role", role.name());
        claims.put("tokenType", "refresh");
        
        return createToken(claims, userDetails.getUsername(), getRefreshTokenExpiration(role));
    }

    /**
     * Create JWT token with claims
     */
    private String createToken(Map<String, Object> claims, String subject, Long expiration) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Extract username from token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract tenant ID from token
     */
    public String extractTenantId(String token) {
        return extractClaim(token, claims -> claims.get("tenantId", String.class));
    }

    /**
     * Extract role from token
     */
    public Role extractRole(String token) {
        String roleStr = extractClaim(token, claims -> claims.get("role", String.class));
        return Role.valueOf(roleStr);
    }

    /**
     * Extract token type from token
     */
    public String extractTokenType(String token) {
        return extractClaim(token, claims -> claims.get("tokenType", String.class));
    }

    /**
     * Extract expiration date from token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract specific claim from token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Check if token is expired
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate token
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Check if token is a refresh token
     */
    public Boolean isRefreshToken(String token) {
        return "refresh".equals(extractTokenType(token));
    }

    /**
     * Check if token is an access token
     */
    public Boolean isAccessToken(String token) {
        return "access".equals(extractTokenType(token));
    }

    /**
     * Get signing key
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Get access token expiration based on role
     */
    private Long getAccessTokenExpiration(Role role) {
        if (role.isReadOnly()) {
            return readonlyAccessTokenExpiration;
        }
        return accessTokenExpiration;
    }

    /**
     * Get refresh token expiration based on role
     */
    private Long getRefreshTokenExpiration(Role role) {
        if (role.isReadOnly()) {
            return readonlyRefreshTokenExpiration;
        }
        return refreshTokenExpiration;
    }

    /**
     * Parse token and handle exceptions
     */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            logger.warn("JWT token is expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            logger.error("JWT token is malformed: {}", e.getMessage());
            throw e;
        } catch (SecurityException e) {
            logger.error("JWT signature validation failed: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("JWT token compact of handler are invalid: {}", e.getMessage());
            throw e;
        }
    }
}
