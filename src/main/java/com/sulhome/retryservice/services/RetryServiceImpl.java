package com.sulhome.retryservice.services;


import com.sulhome.retryservice.errors.AttemptsFailedExceptions;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
@Service
public class RetryServiceImpl implements RetryService {
    @Override
    public <T> T retryCall(Supplier<T> callToRetry, int maxAttempts) throws AttemptsFailedExceptions {
        int attempts = maxAttempts > 0 ? maxAttempts : 3;
        int totalAttempts = 0;
        while (totalAttempts < attempts){
            try {
                return callToRetry.get();
            } catch (Exception exception){
                if(totalAttempts < attempts) {
                    totalAttempts++;
                    continue;
                }
                throw exception;
            }
        }
        throw new AttemptsFailedExceptions("All attempts to call service failed");
    }

    @Override
    public <T> T retryCallReturnDefault(Supplier<T> callToRetry, int maxAttempts, T defaultValue) {
        int attempts = maxAttempts > 0 ? maxAttempts : 3;
        int totalAttempts = 0;
        while (totalAttempts < attempts){
            try {
                return callToRetry.get();
            } catch (Exception exception){
                if(totalAttempts < attempts) {
                    totalAttempts++;
                    continue;
                }
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
