package com.blog.configs;

import com.blog.data.models.CustomUserDetails;
import com.blog.data.models.User;
import com.blog.data.repositories.UserRepository;
import com.blog.domain.exceptions.EmailNotFoundInContextException;
import com.blog.domain.exceptions.UserApplicationContextException;
import com.blog.domain.exceptions.UserNotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationContext {
    private final UserRepository userRepository;

    public ApplicationContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            String email = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
            if (!StringUtils.isEmpty(email)) {
                User user = userRepository.findByEmail(email);
                if (Objects.nonNull(user)) {
                    return user;
                }
                throw new UserNotFoundException("The user with the provided email in the application context could not be found.");
            }
            throw new EmailNotFoundInContextException();
        }
        throw new UserApplicationContextException();
    }
}
