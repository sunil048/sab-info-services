package com.sabtok.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import com.sabtok.util.StringDateConverter;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class CreateTraceable {
	
	@Column(name="CREATED_AT", nullable = false , updatable = false)
	private String createdAt;
	
	@Column(name="CREATED_BY", nullable = false, updatable = false)
	private String createdBy;
	
	@PrePersist
	public void prePersist() {
		
		if (createdAt == null) {
			setCreatedAt(StringDateConverter.getTimeStamp());
		}
		
		if (createdBy == null) {
			setCreatedBy("sab-info");
		}
		
	}
	
}
