package com.sabtok.report.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.dao.TopicDao;
import com.sabtok.entity.Topic;
import com.sabtok.report.service.TopicService;

@Component
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;
	
	@Override
	public Topic saveTopic(Topic topic) {
		topic.setTopicId(topicDao.count()+1);
		return topicDao.save(topic);
	}

	@Override
	public List<Topic> getAllTopic() {
		return topicDao.findAll();
	}

	@Override
	public List<String> getAllTopicNames() {
		return topicDao.getTopicNameList();
	}

	@Override
	public Topic getTopicDetails(Long topicId) {
		Optional<Topic> topic = topicDao.findById(topicId);
		if(topic.isPresent())
			return topic.get();
		else
			return null;
	}

}
