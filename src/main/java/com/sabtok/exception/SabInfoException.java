/**
 *  * @author user
 *    Sep 20, 2023 8:58:22 AM
 */
package com.sabtok.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 * Sep 20, 2023 8:58:22 AM
 */
@Getter
@Setter
public class SabInfoException extends Throwable {

	private String errorCode;
	private String errorMessage;
	
	public SabInfoException(String code, String message) {
		this.errorCode = code;
		this.errorMessage = message;
	}

	@Override
	public String toString() {
		return "SabInfoException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
