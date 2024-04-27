package com.blog.domain.exceptions;

public class EmailNotFoundInContextException extends RuntimeException {
    public EmailNotFoundInContextException() {
        super("Email not found in application context");
    }
}
