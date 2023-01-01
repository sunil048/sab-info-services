package com.sabtok.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
//import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="BOOKS")
public class Book implements Serializable{

	@Id
	@Column(name="BOOKID")
	private String bookId;
	
	@GeneratedValue
	@Column(name="BOOKNO")
	private int bookNo;
	
	@Column(name="BOOKNAME")
	//@NotEmpty(message="Book name is mandatory")
	private String bookName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_DATE")
	private String createdDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;

}
