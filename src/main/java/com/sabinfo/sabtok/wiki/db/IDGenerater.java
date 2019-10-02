package com.sabinfo.sabtok.wiki.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDGenerater {

	/*public static String idGenerater(){
		long limit = 100000L;
		long id = 0;
		id = new Date().getTime() % limit;
		System.out.println("id is "+id);
		return "SAB"+id;
	}*/
	
	public static enum ACTIVITY { ADDED, UPDATED, DELETED }  
	
	public static String idGenerater(long limit){
		/*long id = 0;
		id = new Date().getTime() % limit;
		System.out.println("id is "+id);
		return "SAB"+id;*/
		int randomPIN = (int)(Math.random()*90000000)+1000;
		return "SAB"+randomPIN;
	}
	public static String idGenerater(){
		int randomPIN = (int)(Math.random()*900000)+1000;
		return "SAB"+randomPIN;
	}
	
	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(new Date());
	}
}
