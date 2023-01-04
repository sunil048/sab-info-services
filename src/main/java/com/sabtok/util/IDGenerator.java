package com.sabtok.util;

import java.util.UUID;

public class IDGenerator {
	
	public static Long getPageId() {
		int randomPIN = (int)(Math.random()*900000000)+1000;
		return Long.valueOf(randomPIN);
	}
	
	public static Long getDocumentId() {
		int randomPIN = (int)(Math.random()*900000000)+1000;
		return Long.valueOf(randomPIN);
	}

	public static String getPageActivityGenerator() {
		return UUID.randomUUID().toString();
	}
}
