package com.myservice.api.greeting.service;

import org.springframework.stereotype.Service;

/**
 * Service for greetings.
 *
 * @author omprakash
 */
@Service
public class GreetingService {

    /**
     * Get a public greeting.
     *
     * @return
     */
    public String getPublicGreeting() {
        return "Hello from the other side!";
    }

    /**
     * Get a greeting for a user.
     *
     * @return
     */
    public String getGreetingForUser(String username) {
        return String.format("Hello %s!", username);
    }
}