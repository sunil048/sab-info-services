package com.sabtok.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "ExceedAuthClient", // <-- Use a unique bean name
        url = "${feign.client.exceed.url}" // No security configuration property here
)
public interface ExceedAuthClient {

    @PostMapping("/auth/login")
    String login(@RequestParam("userName") String userName);
}
