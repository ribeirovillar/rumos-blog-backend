package com.blog.domain.exceptions;

public class UserApplicationContextException extends RuntimeException {
    public UserApplicationContextException() {
        super("User not found in application context");
    }
}
