package com.sabinfo.sabtok.wiki.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBUtil {
/*	
	
	public int getBookNo(String tableName) {
		String url = "jdbc:mysql://192.168.92.128:3306/sabwiki_development";
		String user = "root";
		String password = "admin1";
		Connection con;
		int no = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			ResultSet rs;
			if (tableName.equals("BOOKS"))
				rs = stmt.executeQuery("select max(BOOKNO) from BOOKS where BOOKNO>0");
			else if (tableName.equals("DOCUMENTS"))
				rs = stmt.executeQuery("select max(DOCUMENT_NO) from DOCUMENTS where DOCUMENT_NO>0");
			else
				rs = stmt.executeQuery("select max(PAGENO) from PAGES where PAGENO > 0 and ");
			while (rs.next()) {
				no = rs.getInt(1);
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return no;
	}

	public int getPageNo(String bookId){
		String url = "jdbc:mysql://192.168.92.128:3306/sabwiki_development";
		String user = "root";
		String password = "admin1";
		Connection con;
		int no = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			ResultSet rs;
				rs = stmt.executeQuery("select max(PAGENO) from PAGES where BookId="+"'"+bookId+"'");
			while (rs.next()) {
				no = rs.getInt(1);
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return no;
	}
	
	public int getDocumentNo(String pageId) {
		String url = "jdbc:mysql://192.168.92.128:3306/sabwiki_development";
		String user = "root";
		String password = "admin1";
		Connection con;
		int no = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			ResultSet rs;
				rs = stmt.executeQuery("select max(DOCUMENT_NO) from DOCUMENTS where PAGE_ID ="+"'"+pageId+"'");
			while (rs.next()) {
				no = rs.getInt(1);
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return no;
	}*/
	
}
