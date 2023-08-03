package com.sulhome.retryservice.services;


import com.sulhome.retryservice.errors.AttemptsFailedExceptions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RetryServiceImplTest {
    @Test
    public void retryCall_should_retry_max_attempts() throws AttemptsFailedExceptions {
        RetryServiceImpl retryService = new RetryServiceImpl();
        Supplier<Integer> supplierMock = mock(Supplier.class);
        when(supplierMock.get())
                .thenThrow(new RuntimeException("First attempt failed"))
                .thenThrow(new RuntimeException("Second attempt failed"))
                .thenReturn(1);
        int result = retryService.retryCall(supplierMock, 3);
        verify(supplierMock, times(3)).get();
        assertEquals(1, result);
    }

    @Test
    public void retryCallReturnDefault_should_retry_max_attempts_then_return_default() throws AttemptsFailedExceptions {
        RetryServiceImpl retryService = new RetryServiceImpl();
        Supplier<Integer> supplierMock = mock(Supplier.class);
        when(supplierMock.get())
                .thenThrow(new RuntimeException("First attempt failed"))
                .thenThrow(new RuntimeException("Second attempt failed"))
                .thenThrow(new RuntimeException("Third attempt failed"));
        int result = retryService.retryCallReturnDefault(supplierMock, 3,2);
        verify(supplierMock, times(3)).get();
        assertEquals(2, result);
    }
}