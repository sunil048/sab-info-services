package com.sabtok;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sabtok.dao.UserStoryDao;
import com.sabtok.entity.UserStory;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Vector;

@Component
public class UserStoriesScheduler {

    private ObjectMapper mapper;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserStoryDao usdao;

   // @Scheduled(fixedDelay = 1000)
    public void run() {

    }

    @PostConstruct
    public void init(){
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public Object getUserStoryData(){
        System.out.println("Hello Scheduler");
        JSONParser jsone = new JSONParser();
        List<UserStory> userStoryList = new Vector<>();
        try {
            ResponseEntity<String> response = template.getForEntity("http://laptop-paulleg2:5001/user/list/ALL",String.class);
            //System.out.println(response.getBody());
            Object obj = jsone.parse(response.getBody());
            JSONArray jsonObjectList = (JSONArray) obj;

            jsonObjectList.forEach( jsonObj -> {
                UserStory prop =  (UserStory) convertStringToObject(jsonObj.toString(), UserStory.class);
                userStoryList.add(prop);
            });
            usdao.saveAll(userStoryList);
            return userStoryList;
           // System.out.println(userStoryList);
        }catch (Exception e) {
            return "Error "+e.getMessage();
        }
    }

    private <T> Object convertStringToObject(String jsonInputData, Class<T> t) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonInputData, t);
        } catch (Exception e) {
            return null;
        }
    }

}
