package com.sulhome.retryservice.services;

import com.sulhome.retryservice.errors.AttemptsFailedExceptions;

import java.util.function.Supplier;

public interface RetryService {
    <T> T retryCall(Supplier<T> callToRetry, int maxAttempts) throws AttemptsFailedExceptions;
    <T> T retryCallReturnDefault(Supplier<T> callToRetry, int maxAttempts, T defaultValue);
}