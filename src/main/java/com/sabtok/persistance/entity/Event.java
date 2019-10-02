package com.sabtok.persistance.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="EVENTS")
//@SequenceGenerator(name="LOG_SEQUENCE",initialValue=1, allocationSize=10000)
@JsonInclude(content = JsonInclude.Include.NON_NULL)
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
	
	private EventAction action;
	
	@Column(name="Remark")
	private String remark;
	
	@Column(name="DocumentId")
	private String documentId;
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
	public long getLogId() {
		return eventId;
	}

	public void setLogId(long logId) {
		this.eventId = logId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	
	public EventAction getAction() {
		return action;
	}

	public void setAction(EventAction action) {
		this.action = action;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
