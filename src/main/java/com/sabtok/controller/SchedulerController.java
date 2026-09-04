package com.sabtok.controller;

import com.sabtok.schedule.PageLinkageScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    @Autowired
    private PageLinkageScheduler pageLinkageScheduler;

    @GetMapping("/user")
    public void triggerUserStory() {
        pageLinkageScheduler.userStoryRunner();
    }

}
