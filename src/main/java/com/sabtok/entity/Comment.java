package com.sabtok.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="COMMENTS")
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Comment extends CreateTraceable {

	@Id
	@GeneratedValue(generator="comment_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="comment_seq",sequenceName="comment_sequence", initialValue = 1, allocationSize=1)
	@Column(name="ID")
	private Long id;
	
	@Lob
	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="QUESTION_ID")
	private Long questionId;

}
