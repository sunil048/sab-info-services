package com.sabtok.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.datetime.joda.LocalDateTimeParser;

public class StringDateConverter {
	
	public static Date getDateFromString(String sDate1)  {
		//String sDate1 ="31/12/1998";  
		System.out.println("String date is "+sDate1);
		    Date date1 = null;
			try {
				date1 = new SimpleDateFormat("E dd/MM/YYYY HH:mm:ss").parse(sDate1);
			} catch (ParseException e) {
				try {
					date1 = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").parse(sDate1);
				} catch (ParseException e1) {
					try {
						date1 = new SimpleDateFormat("dd-MM-YYYY").parse(sDate1);
					} catch (ParseException e2) {
						try {
							date1 = new SimpleDateFormat("dd-MMM-YYYY").parse(sDate1);
						} catch (ParseException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
					}
				}
			}  
		    System.out.println("new date is "+"\t"+date1);  
		    return date1;
	}
	public static void main(String[] args) throws ParseException {
		String date1 = "17-04-2017 21:40:05";
		String date2 = "17-04-2017";
		//getDateFromString(LocalDateTime);
		System.out.println(LocalDateTime.now());
	}
	
	public static String getTimeStamp() {
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timsStamp = Timestamp.valueOf(localDateTime);
		return timsStamp.toString();
	}
}
