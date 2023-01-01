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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="DOCUMENTS")
@JsonIgnoreProperties(value = { "content" })
@Getter
@Setter
@NoArgsConstructor
@ToString
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

	public int compareTo(Attachement o) {
		return (this.attachementNo)-o.getAttachementNo();
	}
}
