package com.sabtok.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCalls {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * public RestCalls() { HttpHeaders headers = new HttpHeaders(); String authStr
	 * = "admin:admin"; String base64creads =
	 * Base64.getEncoder().encodeToString(authStr.getBytes()); headers.add(authStr,
	 * base64creads); headers.add("Authorization", "Basic " ); //
	 * headers.setBasicAuth("admin", "admin"); ((Object)
	 * headers).setBasicAuth("admin", "admin"); request = new HttpEntity(headers); }
	 */
	
	public String getValue(String url) {
		
		ResponseEntity<String> response  = restTemplate.getForEntity(url , String.class);
		String json = response.getBody();
	    return json;
	}
}
