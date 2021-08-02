package com.chicorski.chicofoodapi.infrastructure.service.email;

public class EmailException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }
}
