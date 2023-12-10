package net.nazariiboiko.wordapi.exception;

import org.hibernate.service.spi.ServiceException;

public class AuthenticationException extends ServiceException {
    public AuthenticationException(String message) {
        super(message);
    }
}
