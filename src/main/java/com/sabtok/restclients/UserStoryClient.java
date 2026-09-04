package com.sabtok.restclients;

import com.sabtok.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "userFeignClient",
        configuration = FeignSecurityConfig.class,
        url = "${feign.client.exceed.url}")
public interface UserStoryClient {

    @GetMapping("/user/detail/{storyNumber}")
    public Object getUserStoryDetails(@PathVariable("storyNumber") String storyNumber);

}
