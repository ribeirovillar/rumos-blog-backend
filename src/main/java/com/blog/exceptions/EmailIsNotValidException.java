package com.blog.exceptions;

public class EmailIsNotValidException extends RuntimeException{
    public EmailIsNotValidException(String email) {
        super(String.format("Email '%s' is not valid", email));
    }
}
