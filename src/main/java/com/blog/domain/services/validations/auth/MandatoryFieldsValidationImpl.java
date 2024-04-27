package com.blog.domain.services.validations.auth;

import com.blog.data.models.User;
import org.springframework.stereotype.Component;

@Component
public class MandatoryFieldsValidationImpl implements RegisterValidations {
    @Override
    public void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getPerson() == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        if (user.getPerson().getFirstName() == null) {
            throw new IllegalArgumentException("First name cannot be null");
        }
        if (user.getPerson().getLastName() == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }
}
