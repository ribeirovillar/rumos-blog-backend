package com.blog.domain.services.validations.auth;

import com.blog.data.models.User;
import com.blog.domain.services.validations.CreateValidations;
import org.springframework.stereotype.Component;

@Component
public class AuthMandatoryFieldValidation implements CreateValidations<User> {
    @Override
    public void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getPerson() == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        if (user.getPerson().getFirstName() == null || user.getPerson().getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Please, inform a first name");
        }
        if (user.getPerson().getLastName() == null || user.getPerson().getLastName().isEmpty()) {
            throw new IllegalArgumentException("Please, inform a last name");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Please, inform an email");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Please, inform a valid password");
        }
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }
}
