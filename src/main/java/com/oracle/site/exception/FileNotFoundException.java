package com.oracle.site.exception;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
