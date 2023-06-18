package com.github.felipetomazec.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtException extends AuthenticationException {
    public InvalidJwtException() {
        super("The informed JWT token is either invalid or expired.");
    }
}
