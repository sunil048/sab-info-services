package com.sabtok.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "plmFeignClient", url = "${feign.client.plm.url}")
public interface PlmClient {

    @GetMapping("/issue/getIssue/{issueId}")
    public Object getIssueDetails(@PathVariable("issueId") String issueId);
}
