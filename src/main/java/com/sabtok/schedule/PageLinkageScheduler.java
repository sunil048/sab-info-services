package com.sabtok.schedule;

import com.sabtok.entity.LinkageStatus;
import com.sabtok.entity.LinkageType;
import com.sabtok.entity.PageLinkage;
import com.sabtok.restclients.PlmClient;
import com.sabtok.restclients.UserStoryClient;
import com.sabtok.services.PageService;
import com.sabtok.util.StringDateConverter;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PageLinkageScheduler {

    private PageService pageService;
    private UserStoryClient userStoryClient;
    private PlmClient plmClient;
    List<PageLinkage> links;

    @Scheduled(cron = "${linkage.scheduler.incident.cron}")
    public void incidentRunner() {
        System.out.println("Running scheduler incident runner");
        Long startTime = System.currentTimeMillis();
        links = pageService.getAllNotProceessedLinks();
        List<PageLinkage> userStoryList =  links.stream()
                .filter(lnk -> lnk.getLinkageType().equals(LinkageType.INCIDENT))
                .collect(Collectors.toList());

        List<PageLinkage> updatedLinks = new ArrayList<>();
        userStoryList.forEach(pl -> {
            try {
                Map<String, String> response = (Map<String, String>) plmClient.getIssueDetails(pl.getItemId());
                String storyDescription = response.get("issueID") +" : " + response.get("description");
                if (storyDescription != null || storyDescription != "" ) {
                    pl.setDescription(storyDescription);
                    pl.setStatus(LinkageStatus.SUCCESS);
                    updatedLinks.add(pl);
                }
            } catch (Exception e){
                pl.setError(e.getMessage());
                pl.setStatus(LinkageStatus.FAILED);
                updatedLinks.add(pl);
            }

        });

        if (!updatedLinks.isEmpty()){
            pageService.updateLinks(updateStatusField(updatedLinks));
        }
        System.out.println("Completed scheduler incident runner in "+ (System.currentTimeMillis() - startTime) + " mil seconds");
    }

    @Scheduled(cron = "${linkage.scheduler.user.story.cron}" )
    public void userStoryRunner(){
        System.out.println("Started scheduler user story runner");
        Long startTime = System.currentTimeMillis();
        links = pageService.getAllNotProceessedLinks();
        List<PageLinkage> userStoryList =  links.stream()
                .filter(lnk -> lnk.getLinkageType().equals(LinkageType.USER_STORY))
                .collect(Collectors.toList());

        List<PageLinkage> updatedLinks = new ArrayList<>();
        userStoryList.forEach(pl -> {
            try {
                Map<String, String> response = (Map<String, String>) userStoryClient.getUserStoryDetails(pl.getItemId());
                String storyDescription = response.get("discription");
                if (storyDescription != "" || storyDescription != null) {
                    pl.setDescription(storyDescription);
                    pl.setStatus(LinkageStatus.SUCCESS);
                    updatedLinks.add(pl);
                }
            } catch (Exception e){
                pl.setError(e.getMessage());
                pl.setUpdatedAt(StringDateConverter.getTimeStamp());
                pl.setStatus(LinkageStatus.FAILED);
                updatedLinks.add(pl);
            }

        });
        if (!updatedLinks.isEmpty()){
            pageService.updateLinks(updateStatusField(updatedLinks));
        }
        System.out.println("Completed scheduler user story runner "+  (System.currentTimeMillis() - startTime) + " mili seconds.");
    }

    private Set<PageLinkage> updateStatusField(List<PageLinkage> links){
       return  links.stream().map(link -> {
            link.setUpdatedBy("linkage-scheduler");
            link.setUpdatedAt(StringDateConverter.getTimeStamp());
            link.setRetryCount(link.getRetryCount() + 1);
            return link;
        }).collect(Collectors.toSet());
    }
}
