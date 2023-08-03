package com.sulhome.retryservice.errors;

public class AttemptsFailedExceptions extends Exception {
    public AttemptsFailedExceptions(String message) {
        super(message);
    }
}
