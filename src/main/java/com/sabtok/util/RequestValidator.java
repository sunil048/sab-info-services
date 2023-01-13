package com.sabtok.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sabtok.entity.Page;
import com.sabtok.validator.ValidatorResponse;

@Service
public class RequestValidator {
	
	public ValidatorResponse validatePageRequest(Page page) {
		ValidatorResponse validatorResponse = new ValidatorResponse();
		validatorResponse.setValidationFailure(false);
		StringBuilder errorMessage = new StringBuilder();
		validateRequestPage(validatorResponse,errorMessage,page);
		return validatorResponse;
	}

	private void validateRequestPage(ValidatorResponse validatorResponse, StringBuilder errorMessage, Page page) {
	
		if (ObjectUtils.isEmpty(page)) {
			validatorResponse.setValidationFailure(true);
			errorMessage.append("Page Object is blank");
			validatorResponse.setFailureReason(errorMessage.toString());
		}
		helperNullCheck("PAGE ID",page.getPageId(),validatorResponse,errorMessage);
	}
	
	private void helperNullCheck(String fieldName, String fieldObj,ValidatorResponse validatorResponse, StringBuilder errorMessage) {
		if (StringUtils.isBlank(fieldObj)) {
			validatorResponse.setValidationFailure(true);
			errorMessage.append(fieldName);
			errorMessage.append(" is blank.");
		}
	}
	
	public static <T> boolean isAnyEquals(final T val,final T...vals) {
		return Arrays.asList(vals).stream().anyMatch(v -> v.equals(val));
	}
	
}
