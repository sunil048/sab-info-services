package com.sabtok.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="ACTIVITIES_PAGE")
public class PageActivity {

	@Id
	@Column(name="ACTIVITY_ID")
	private String activityId;
	
	@Column(name="PAGE_ID")
	private String pageId;
	
	@Column(name="ACTION")
	private String action;
	
	@Column(name="DATE")
	private String date;
	
	@Column(name="OLD_CONTENT")
	@Lob
	private String oldContent;
	
	@Column(name="NEW_CONTENT")
	@Lob
	private String newContent;
	
	@Column(name="UPADTE_CREATEd_BY")
	private String updateCreatedBy;
	
}
