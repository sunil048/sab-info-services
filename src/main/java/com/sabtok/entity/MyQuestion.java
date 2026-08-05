package com.sabtok.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Entity
@Table(name="QUESTIONERIES")
@JsonIgnoreProperties({"comments", "hibernateLazyInitializer", "handler"})
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class MyQuestion extends CreateTraceable {

	@Id
	@GeneratedValue(generator="quesion_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="quesion_seq",sequenceName="questions_sequence", initialValue = 1, allocationSize=1)
	@Column(name="ID")
	private long questionId;
	
	@Column(name="QUESTION")
	private String question;

	@Column(name="CATEGORY")
	private String category;

	@Column(name="SUB_CATEGORY")
	private String subCategory;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="SKILL")
	private String skill;

	@Column(name="SUB_SKILL")
	private String subSkill;

	@Column(name="PRIORITY")
	private String priority;

	@Column(name="LEVEL")
	private String level;

	@Column(name="STATUS")
	private String status;

	@Column(name="UPDATED_AT")
	private LocalDateTime updatedAt;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="LABEL")
	private String label;

	@Column(name="NO_OF_PRACTICE")
	private String noOfPractice;

	@Column(name="NO_OF_ATTEMPTED")
	private String noOfAttempted;

	@Column(name="LINKED_QUESTIONS_NO")
	private String linkedQuestionNos;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUESTION_ID")
	private List<Comment> comments;

	@PreUpdate
	public void preUpdate() {
		if (updatedAt == null){
			this.updatedAt = LocalDateTime.now();
		}
		if(updatedBy == null) {
			updatedBy = "sab-info-services";
		}
	}

	/*
	The reason you are seeing the PropertyValueException for createdAt is a JPA lifecycle limitation: Hibernate does not automatically merge multiple @PrePersist hooks across an inheritance hierarchy unless they are defined or handled explicitly.
	Because your child class MyQuestion defines its own @PrePersist method, it completely overrides and ignores the @PrePersist method inside the CreateTraceable parent class.
	Therefore, the parent's prePersist() never runs, leaving createdAt and createdBy as null when hitting the database.
	 */

	@PrePersist
	public void prePersist() {
		super.prePersist();//if not added ignores parent persist
		if (updatedAt == null){
			this.updatedAt = LocalDateTime.now();
		}
		if(updatedBy == null) {
			updatedBy = "sab-info-services";
		}
	}
	
}
