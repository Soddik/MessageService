package com.soddik.messageapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus status;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
