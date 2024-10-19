package com.sabtok;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userFeignClient", url = "http://laptop-paulleg2:5001/user/")
public interface UserStoryClient {

    @GetMapping("/detail/{id}")
    public Object getUserStoryDetails(@PathVariable("id") String id);
}
