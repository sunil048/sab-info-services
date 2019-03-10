package com.sabtok.persistance.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="PAGES")
@SequenceGenerator(name="PAGE_SEQUENCE", initialValue=1, allocationSize=100)
public class Page implements Serializable,Comparable<Page>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PAGE_SEQUENCE")
	@Column(name="PAGENO")
	private Long pageNo;
	
	@Column(name="PAGEID")
	private String pageId;
	
	
	@Column(name="BOOKID")
	private String bookId;
	
	@Column(name="BOOKNAME")
	private String bookName;
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/*@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageNo=" + pageNo + ", bookId=" + bookId + ", title=" + title + "]";
	}*/

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String content;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@OneToOne
	private Book book;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int compareTo(Page o) {
		return (int) ((this.pageNo)-o.getPageNo());
	}

}
