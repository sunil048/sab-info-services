package com.sabtok.persistance.entity;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sabtok.util.DateHandler;

@Entity
@Table(name="BOOKS")
@SequenceGenerator(name="BOOK_SEQUENCE", initialValue=1, allocationSize=100)
public class Book implements Serializable ,Comparable<Book>{
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookNo=" + bookNo + ", bookName=" + bookName + ", description="
				+ description + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}


	@Column(name="BOOKID")
	private String bookId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BOOK_SEQUENCE")
	@Column(name="BOOKNO")
	private Long bookNo;
	
	@Column(name="BOOKNAME")
	@NotEmpty(message="Book name is mandatory")
	private String bookName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@OneToMany
	private List<Page> pages;

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Long getBookNo() {
		return bookNo;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int compareTo(Book o) {
		 return (this.bookName).compareTo(o.getBookName());
		// return 0; maintain same order
	}
	

	
}
