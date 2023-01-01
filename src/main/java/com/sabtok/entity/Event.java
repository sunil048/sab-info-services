package com.sabtok.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="EVENTS")
//@SequenceGenerator(name="LOG_SEQUENCE",initialValue=1, allocationSize=10000)
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EventId")
	private long eventId;
	
	@Column(name="BokkId")
	private String bookId;
	
	@Column(name="PageId")
	private String pageId;
	
	@Column(name="CreatedBy")
	private String createdBy;
	
	@Column(name="ModifiedDate")
	private Date lastModifiedDate;
	
	@Column(name="ModifiedBy")
	private String modifiedBy;
	
	private PageEventAction action;
	
	@Column(name="Remark")
	private String remark;
	
	@Column(name="DocumentId")
	private String documentId;
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
}
