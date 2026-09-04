package com.sabtok.config;

import com.sabtok.restclients.ExceedAuthClient;
import com.sabtok.restclients.UserStoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RequiredArgsConstructor
public class JwtTokenManagerService {

    private String JWT_TOKEN;

    private final ExceedAuthClient exceedAuthClient;

    public String getToken() {
        return exceedAuthClient.login("sab-info-services");
    }

}
