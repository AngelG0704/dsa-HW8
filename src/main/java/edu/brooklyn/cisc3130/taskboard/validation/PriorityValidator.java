package edu.brooklyn.cisc3130.taskboard.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<ValidPriority, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return value.equalsIgnoreCase("LOW") ||
                value.equalsIgnoreCase("MEDIUM") ||
                value.equalsIgnoreCase("HIGH");
    }
}