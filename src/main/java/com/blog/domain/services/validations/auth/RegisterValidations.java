package com.blog.domain.services.validations.auth;

import com.blog.data.models.User;

public interface RegisterValidations {
    void validate(User user);
}
