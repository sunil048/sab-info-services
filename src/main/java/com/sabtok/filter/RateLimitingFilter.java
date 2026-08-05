package com.sabtok.filter;

import com.sabtok.config.TokenBucketRateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RateLimitingFilter implements Filter {

    private final TokenBucketRateLimiter rateLimiter;

    public RateLimitingFilter(TokenBucketRateLimiter tokenBucketRateLimiter){
        this.rateLimiter = tokenBucketRateLimiter;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!rateLimiter.allowRequest()) {
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"error\": \"Too many requests. Please try again later.\"}");
            return; // Block the request from proceeding
        }

        chain.doFilter(request, response); // Allow request to pass to the Controller

    }
}
