package com.sabtok.entity;

public enum StatusCode {
	
	ACTIVE("A"),
	
	INACTIVE("I"),
	
	DELETED("D");
	
	private String code;

	StatusCode(String string) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	
}
