/**
 * 
 */
package com;

/**
 * @author Sunil
 *
 * PlmException.java Aug 6, 2020 12:37:14 PM
 */
public class PlmException extends Exception {

	private String errorCode = null;
	private String errorMessage = null;
	private String errorException = null;
	
	public PlmException(Throwable cause) {
		super(cause);
		if (cause != null) {
			this.errorException = cause.toString();
			this.errorMessage = cause.getMessage();
		}
		System.out.println(cause.toString());
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorException() {
		return errorException;
	}

	public void setErrorException(String errorException) {
		this.errorException = errorException;
	}
	
	
	
}
