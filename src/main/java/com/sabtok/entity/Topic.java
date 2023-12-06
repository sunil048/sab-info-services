package com.sabtok.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="TOPICS")
public class Topic extends Traceable{

	@Id
	@Column(name="TOPIC_ID")
	private Long topicId;
	
	@Column(name="TOPIC_NAME", nullable = false)
	private String topicName;
	
	@Column(name="DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name="SKILL", nullable = false)
	private String skillName;
	
	@Column(name="RATING", nullable = false)
	private Long rating;
}
