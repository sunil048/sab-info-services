package com.sabtok.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sabtok.util.IDGenerator;

public class ReportUtil {
	
	public static Long getJobId() {
		int randomPIN = (int)(Math.random()*900000000)+1000;
		return Long.valueOf(randomPIN);
	}
	
	public static String getTimeStamp() {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now());
	}
	public static String generate_report_Id() {
		return "REPT"+String.valueOf(IDGenerator.getPageId());
	}
}
