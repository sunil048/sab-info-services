package com.sabtok.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sabtok.entity.Topic;

@Service
public interface TopicService {

	public Topic saveTopic(Topic topic);
	public List<Topic> getAllTopic();
	public List<String> getAllTopicNames();
	public Topic getTopicDetails(Long topicId);
	
}
