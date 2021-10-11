package com.sabtok.persistance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="BOOKS")
public class Book implements Serializable ,Comparable<Book>{
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookNo=" + bookNo + ", bookName=" + bookName + ", description="
				+ description + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}

	@Id
	@Column(name="BOOKID")
	private String bookId;
	
	
	@GeneratedValue
	@Column(name="BOOKNO")
	private int bookNo;
	
	@Column(name="BOOKNAME")
	@NotEmpty(message="Book name is mandatory")
	private String bookName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_DATE")
	private String createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
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

	public int compareTo(Book o) {
		 return (this.bookName).compareTo(o.getBookName());
		// return 0; maintain same order
	}
	
}
