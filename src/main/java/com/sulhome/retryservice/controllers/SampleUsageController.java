package com.sulhome.retryservice.controllers;

import com.sulhome.retryservice.services.RetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleUsageController {
    private final RetryService retryService;

    public SampleUsageController(RetryService retryService) {
        this.retryService = retryService;
    }

    @GetMapping("/sample")
    public String getResult() {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";

        RestTemplate restTemplate = new RestTemplate();

        return retryService.retryCallReturnDefault(
                () -> restTemplate.getForObject(apiUrl, String.class), 3,"No result" );
    }
}
