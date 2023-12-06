package com.sabtok.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.util.StringUtils;
import com.sabtok.entity.StatusCode;
import com.sabtok.util.StringDateConverter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class Traceable {
	
	private String user="sab-info";

	@Column(name="STATUS_CODE", nullable = false)
	private String statusCode;
	
	@Column(name="CREATED_AT", nullable = false , updatable = false)
	private String createdAt;
	
	@Column(name="CREATED_BY", nullable = false, updatable = false)
	private String createdBy;
	
	@Column(name="UPDATED_BY", nullable = false)
	private String updatedBy;
	
	@Column(name="UPDATED_AT", nullable = false)
	private String updatedAt;
	
	@PrePersist
	public void prePersist() {
		if (StringUtils.isEmpty(statusCode)) {
			setStatusCode("A");
		}
		
		if (createdAt == null) {
			setCreatedAt(StringDateConverter.getTimeStamp());
		}
		
		if (createdBy == null) {
			setCreatedBy(user);
		}
		
		preUpdate();
	}
	
	@PreUpdate
	public void preUpdate() {
		if (updatedAt == null) {
			setUpdatedAt(StringDateConverter.getTimeStamp());
		}
		
		if (updatedBy == null) {
			setUpdatedBy(user);
		}
	}
}
