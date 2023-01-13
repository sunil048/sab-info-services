package com.sabtok.validator;


public class ValidatorResponse {

	private boolean validationFailure;
	private String failureReason;
	
	public boolean isValidationFailure() {
		return validationFailure;
	}
	public void setValidationFailure(boolean validationFailure) {
		this.validationFailure = validationFailure;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	
}
