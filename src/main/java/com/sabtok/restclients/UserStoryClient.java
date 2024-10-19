package com.sabtok.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userFeignClient", url = "${feign.client.exceed.url}")
public interface UserStoryClient {

    @GetMapping("/user/detail/{storyNumber}")
    public Object getUserStoryDetails(@PathVariable("storyNumber") String storyNumber);

}
