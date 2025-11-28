package com.educationerp.core.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Custom naming strategy for database tables and columns
 * Implements snake_case naming convention as specified
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public class TayfunAksayNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return Identifier.toIdentifier(convertToSnakeCase(name.getText()));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return Identifier.toIdentifier(convertToSnakeCase(name.getText()));
    }

    /**
     * Convert camelCase or PascalCase to snake_case
     */
    private String convertToSnakeCase(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        StringBuilder result = new StringBuilder();
        char firstChar = name.charAt(0);
        result.append(Character.toLowerCase(firstChar));

        for (int i = 1; i < name.length(); i++) {
            char currentChar = name.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append('_');
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
