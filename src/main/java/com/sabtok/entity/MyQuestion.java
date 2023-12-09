package com.sabtok.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="QUESTIONERIES")
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
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="SKILL")
	private String skill;
	
	@Column(name="STATUS")
	private String status;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUESTION_ID")
	private List<Comment> comments;
	
}
