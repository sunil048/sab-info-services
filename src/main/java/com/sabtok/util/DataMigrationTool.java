package com.sabtok.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.File;

import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Page;



public class DataMigrationTool {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		/*BufferedReader br = new BufferedReader(new FileReader(new java.io.File("database.txt")));
		while(br.readLine() != null) {
			System.out.println(br.read());
		}*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sabwiki_development","root","admin1");
		Statement stmt = con.createStatement();
		//ResultSet rs = stmt.executeQuery("select * from Books");
		ResultSet rs = stmt.executeQuery("select * from Pages");
		List <Book> oldData = new ArrayList<Book>();
		List <Page> oldPageData = new ArrayList<Page>();
		/*
		 * while(rs.next()) { Book book = new Book(); book.setBookId(rs.getString(1));
		 * book.setBookName(rs.getString(2));
		 * book.setBookNo(Long.valueOf(rs.getString(3)));
		 * 
		 * 
		 * book.setDescription(rs.getString(4)); book.setCreatedDate(new
		 * Date(StringDateConverter.getDateFromString(rs.getString(5)).getTime()));
		 * book.setCreatedBy(rs.getString(6));
		 * 
		 * //System.out.println("converting string date into date");
		 * 
		 * //break; oldData.add(book); }
		 */
		while(rs.next()) {
			Page page = new Page();
			page.setPageId(rs.getString("PAGEID"));
			page.setBookId(rs.getString("BOOKID"));
			page.setBookName(rs.getString("BOOKNAME"));
			
			page.setContent(rs.getString("CONTENT"));
			page.setPageNo(Long.valueOf(rs.getInt("PAGENO")));
			page.setTitle(rs.getString("TITLE"));
			oldPageData.add(page);
		}
		System.out.println("Size of data is "+oldPageData.size());
		rs.close();
		con.close();	
		Class.forName("org.postgresql.Driver");
		 con = DriverManager.getConnection("jdbc:postgresql://192.168.92.137:5432/sabwiki_development","postgres","admin1");
		 //String query = "insert into books values (?,?,?,?,?,?)";
		 String query = "insert into pages values (?,?,?,?,?,?,?,?)";
		 PreparedStatement ps = con.prepareStatement(query);
			/*
			 * for(Book book : oldData) { ps.setInt(1,
			 * Integer.valueOf(book.getBookNo().toString())); ps.setString(2,
			 * book.getBookId()); ps.setString(3, book.getBookName()); ps.setString(4,
			 * book.getCreatedBy()); // ps.setDate(5, book.getCreatedDate());
			 * ps.setString(6, book.getDescription());
			 * 
			 * 
			 * // System.out.println(ps.execute()); }
			 */
		// Statement stmt1 = con.createStatement();
		// ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Pages");
		// ResultSetMetaData rsmd = rs1.getMetaData();
		// String name = rsmd.getColumnName(5);
		// System.out.println(name);
		for(Page page : oldPageData) {
			 int pageid = Integer.valueOf(page.getPageNo().toString());
			 System.out.println("page id "+pageid);
			 ps.setInt(1, pageid);
			 ps.setString(2, page.getBookId());
			 ps.setString(3, page.getBookName());
			 ps.setString(4, page.getContent());
			 ps.setString(5, "Test");
			 ps.setNull(6, java.sql.Types.DATE);
			
			 ps.setString(7, page.getPageId());
			 ps.setString(8, page.getTitle());
		 try {
			 System.out.println(ps.execute());
		 }catch(Exception e) {
			 System.out.println("WError");
		 }
		 }
	}
	
	/*
	 * CREATE TABLE public.pages
(
  pageno bigint NOT NULL,
  bookid character varying(255),
  bookname character varying(255),
  content text,
  created_by character varying(255),
  created_date date,
  pageid character varying(255),
  title character varying(255),
  book_bookno bigint,
  page character varying(255),
  CONSTRAINT pages_pkey PRIMARY KEY (pageno),
  CONSTRAINT fkbo3t68gox560wehq4yfhe4i2w FOREIGN KEY (book_bookno)
      REFERENCES public.books (bookno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.pages
	 */
	
	
}
