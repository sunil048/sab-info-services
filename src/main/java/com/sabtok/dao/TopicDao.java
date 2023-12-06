package com.sabtok.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.Topic;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long> {

	@Query("select topicName from Topic")
	List<String> getTopicNameList();
	
	
}
