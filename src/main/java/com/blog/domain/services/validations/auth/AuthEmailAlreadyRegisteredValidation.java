package com.blog.domain.services.validations.auth;

import com.blog.domain.exceptions.EmailAlreadyRegisteredException;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.services.validations.CreateValidations;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class AuthEmailAlreadyRegisteredValidation implements CreateValidations<User> {
    private final UserRepository userRepository;

    public AuthEmailAlreadyRegisteredValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) {
        if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }
    }
}
