package com.blog.domain.services.validations.auth;

import com.blog.domain.exceptions.EmailAlreadyRegisteredException;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class EmailAlreadyRegisteredValidationImpl implements RegisterValidations {
    private final UserRepository userRepository;

    public EmailAlreadyRegisteredValidationImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User user) {
        if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }
    }
}
