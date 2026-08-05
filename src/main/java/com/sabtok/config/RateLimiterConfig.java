package com.sabtok.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public TokenBucketRateLimiter apiRateLimiter() {
        // Allows a burst of 10 requests, refills at 2 tokens per second
        return new TokenBucketRateLimiter(1, 1);
    }
}
