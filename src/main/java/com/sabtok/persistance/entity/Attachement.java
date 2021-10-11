package com.sabtok.persistance.entity;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="DOCUMENTS")
@JsonIgnoreProperties(value = { "content" })
public class Attachement implements Serializable,Comparable<Attachement> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Attachement [attachementId=" + attachementId + ", attachementNo=" + attachementNo + ", title=" + title
				+ ", pageId=" + pageId + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", content="
				+ content + "]";
	}


	@Id
	@Column(name="DOCUMENT_ID")
	private String attachementId;
	
	@Column(name="DOCUMENT_NO")
	@GeneratedValue
	private int attachementNo;
	
	@Column(name="TITLE")
	private String title;
	/*
	@Column(name="BOOK_ID")
	private String bookId;*/
	
	@Column(name="PAGE_ID")
	private String pageId;
	
	@Column(name="CREATED")
	private String createdDate;
	
	public String getAttachementId() {
		return attachementId;
	}

	public void setAttachementId(String attachementId) {
		this.attachementId = attachementId;
	}

	public int getAttachementNo() {
		return attachementNo;
	}

	public void setAttachementNo(int attachementNo) {
		this.attachementNo = attachementNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

/*	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}*/

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}


	@Column(name="AUTHOR")
	private String createdBy;
	
	@Column(name="CONTENT")
	@Lob
	private Blob content;

	public int compareTo(Attachement o) {
		return (this.attachementNo)-o.getAttachementNo();
	}
	
	

}
