package com.blog.domain.exceptions;

public class EmailIsNotValidException extends RuntimeException{
    public EmailIsNotValidException(String email) {
        super(String.format("Email '%s' is not valid", email));
    }
}
