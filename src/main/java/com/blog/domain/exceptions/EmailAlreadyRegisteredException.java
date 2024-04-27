package com.blog.domain.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String email) {
        super(String.format("Email '%s' is already registered", email));
    }

}
