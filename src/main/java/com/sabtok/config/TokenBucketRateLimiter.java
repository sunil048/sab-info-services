package com.sabtok.config;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {

    private final long capacity;
    private final long refillRatePerSecond;

    private long availableTokens;
    private long lastRefillTimestampNanos;

    public TokenBucketRateLimiter(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.availableTokens = capacity;
        this.lastRefillTimestampNanos = System.nanoTime();
    }

    /**
     * Attempts to consume 1 token.
     * @return true if the request is allowed, false otherwise.
     */
    public synchronized boolean allowRequest() {
        refill();

        if (availableTokens >= 1) {
            availableTokens--;
            return true;
        }

        return false;
    }

    /**
     * Lazily refills tokens based on the time elapsed since the last request.
     */
    private void refill() {
        long now = System.nanoTime();
        long elapsedNanos = now - lastRefillTimestampNanos;

        // Calculate newly generated tokens based on elapsed time
        double elapsedSeconds = (double) elapsedNanos / TimeUnit.SECONDS.toNanos(1);
        long tokensToAdd = (long) (elapsedSeconds * refillRatePerSecond);

        if (tokensToAdd > 0) {
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTimestampNanos = now;
        }
    }

}
