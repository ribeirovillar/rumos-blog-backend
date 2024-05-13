package com.blog.domain.services.validations.auth;

import com.blog.data.models.User;
import com.blog.domain.exceptions.EmailIsNotValidException;
import com.blog.domain.services.validations.CreateValidations;
import org.springframework.stereotype.Component;

@Component
public class AuthEmailValidation implements CreateValidations<User> {
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
