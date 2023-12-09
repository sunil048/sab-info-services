package com.sabtok.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="PAGES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Page implements Serializable,Comparable<Page>  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PAGEID")
	private String pageId;
	
	@Column(name="PAGENO")
	private int pageNo;
	
	@Column(name="BOOKID")
	private String bookId;
	
	@Column(name="BOOKNAME")
	private String bookName;
		
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	@Lob
	private String content;
	
	@Column(name="CREATED_DATE")
	private String createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	public int compareTo(Page o) {
		return (this.pageNo)-o.getPageNo();
	}

}
