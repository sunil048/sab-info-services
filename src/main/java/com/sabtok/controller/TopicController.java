package com.sabtok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.entity.Topic;
import com.sabtok.report.service.TopicService;

@RestController
@RequestMapping("/topic")
@CrossOrigin
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	
	@PostMapping("/save")
	public Topic saveTopic(@RequestBody Topic topic) {
		return topicService.saveTopic(topic);
	}
	
	@GetMapping("/list")
	public List<Topic> getTopicList() {
		return topicService.getAllTopic();
	}
	
	@GetMapping("/list/names")
	public List<String> getTopicNames() {
		return topicService.getAllTopicNames();
	}
}
