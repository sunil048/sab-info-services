package com.sabtok.entity;
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
	
	private static final long serialVersionUID = 1L;

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
	
	@Column(name="AUTHOR")
	private String createdBy;
	
	@Column(name="CONTENT")
	@Lob
	private Blob content;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name="FILE_SIZE",columnDefinition = "double default 0.0")
	private double size;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
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
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int compareTo(Attachement o) {
		return (this.attachementNo)-o.getAttachementNo();
	}
	
	@Override
	public String toString() {
		return "Attachement [attachementId=" + attachementId + ", attachementNo=" + attachementNo + ", title=" + title
				+ ", pageId=" + pageId + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", content="
				+ content + "]";
	}
	

}
