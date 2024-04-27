package com.blog.services.validations;

import com.blog.exceptions.EmailIsNotValidException;
import com.blog.models.User;
import org.springframework.stereotype.Component;

@Component
public class EmailValidationImpl implements RegisterValidations {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new EmailIsNotValidException(user.getEmail());
        }
    }
}
