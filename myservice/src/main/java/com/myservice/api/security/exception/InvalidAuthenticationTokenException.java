package com.myservice.api.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown if an authentication token is invalid.
 *
 * @author omprakash
 */
public class InvalidAuthenticationTokenException extends AuthenticationException {

    public InvalidAuthenticationTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
