package com.chicorski.chicofoodapi.infrastructure.service.storage;

public class StorageException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}
