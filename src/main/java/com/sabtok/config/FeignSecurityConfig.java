package com.sabtok.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
@RequiredArgsConstructor
public class FeignSecurityConfig {

    private String JWT_TOKEN;


    private final JwtTokenManagerService jwtTokenManagerService;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            //ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (JWT_TOKEN == null) {
                String rawToken = jwtTokenManagerService.getToken();
                JWT_TOKEN = rawToken.startsWith("Bearer ") ? rawToken : "Bearer " + rawToken;
            }
            requestTemplate.header("Authorization", JWT_TOKEN);
        };
    }
}
