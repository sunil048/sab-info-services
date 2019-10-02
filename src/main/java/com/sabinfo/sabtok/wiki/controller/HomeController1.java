package com.sabinfo.sabtok.wiki.controller;

 
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sabinfo.sabtok.wiki.dao.BookDaoImpl;
import com.sabinfo.sabtok.wiki.db.DBUtil;
import com.sabinfo.sabtok.wiki.db.IDGenerater;
import com.sabinfo.sabtok.wiki.model.Attachement;
import com.sabinfo.sabtok.wiki.model.Book;
import com.sabinfo.sabtok.wiki.model.Page;



@Controller
public class HomeController1 {
	
	@Autowired
	BookDaoImpl bookDAOimpl;
	
	//DBUtil myUtil;
	
	private static Logger logger = Logger.getLogger(HomeController1.class);
	
	@RequestMapping(value="/")
	public String homePage(Map<String, Object> map){
		logger.info("Initialized BookDaoImpl auto wired component ");
		return "home";
	}
	
	@RequestMapping(value="/wiki")
	public String homeBookPage(Map<String, Object> map){
		logger.info("Initialized BookDaoImpl auto wired component ");
		logger.info("Initializing DBUtil class");
		//myUtil = new DBUtil();
		logger.debug("Gettimg total number of books count.");
		
		//Integer bookCount = myUtil.getBookNo("BOOKS");
		Integer bookCount = null;//(Integer) bookDAOimpl.getTotalNumberOfBooks();
		logger.debug("Total number of books are "+bookCount);
		if (bookCount == null)
			bookCount = 0;
		Book book = new Book();
		book.setBookNo(bookCount+1);
		map.put("book", book);
		logger.info("Getting List of all books.");
		logger.debug("bookDAOimpl.getBooks().");
		List <Book> booksList = bookDAOimpl.getBooks();
		//Collections.sort(booksList,new Comp);
		map.put("bookList" ,booksList);
		return "bookHome";
	}
	
	@RequestMapping(value="/save" , method = RequestMethod.POST)
	public String createBook(@ModelAttribute("book") Book book,Map<String, Object> map){
		
			String bookId = IDGenerater.idGenerater();
			book.setBookId(IDGenerater.idGenerater());
			book.setCreatedDate(IDGenerater.getDate());
			bookDAOimpl.saveBook(book);
			//myUtil = new DBUtil();
			//int bookNo = myUtil.getBookNo("BOOKS");
			int bookNo = (Integer) bookDAOimpl.getTotalNumberOfBooks();
			Book book1 = new Book();
			book1.setBookNo(bookNo+1);
			map.put("book", book1);
			map.put("bookList" ,bookDAOimpl.getBooks());
			return "bookHome";
	}
	
	@RequestMapping(value="/getBookDetails/{bookId}", method=RequestMethod.GET)
	public String getBookDetails(@PathVariable("bookId") String bookId,Map<String, Object> map){
		Book book = bookDAOimpl.getBook(bookId);
		map.put("MyBooks", book);
		map.put("book_pages", bookDAOimpl.getPages(bookId));
		return "book";
	}
	
	
	
	@RequestMapping(value="/addPage/{bookId}/{bookName}")
	public String homePage(@PathVariable("bookId") String bookId,@PathVariable("bookName") String bookName,Map<String, Object> map){
		//int pageNo = myUtil.getPageNo(bookId);
		
		Integer pageNo =  (Integer) bookDAOimpl.getTotalNumberOfPages(bookId);
		if (pageNo == null)
			pageNo = 0;
		Page page = new Page();
		page.setBookId(bookId);
		page.setPageNo(pageNo+1);
		page.setBookName(bookName);
		page.setCreatedDate(IDGenerater.getDate());
		//page.setPageId(pageId);
		//System.out.println(page);
		map.put("url", "savePage");
		map.put("submitButton","Add Page");
		map.put("Page", page);
		return "homePage";
	}

	@RequestMapping(value="/savePage")
	public String addPage(@ModelAttribute("Page")Page page,Map<String, Object> map){
		String pageId = IDGenerater.idGenerater(100000000);
		page.setPageId(pageId);
		bookDAOimpl.savePage(page);
		map.put("url", "updatePageDetails");
		map.put("submitButton","Upadte");
		Page newPage = bookDAOimpl.getPageDetails(pageId);
		map.put("Page",newPage );
		map.put("pageList", bookDAOimpl.getPages(newPage.getBookId()));
		
		//int attachementNo = myUtil.getDocumentNo(newPage.getPageId());
		
		Integer attachementNo = (Integer) bookDAOimpl.getTotalNumberOfAttachedDocs(newPage.getPageId());
		if (attachementNo == null)
			attachementNo = 0;
		map.put("attachementNo",attachementNo+1);
		map.put("documentList", bookDAOimpl.getAttachedDocuments(newPage.getPageId()));
		return "homePage";
	}
	
	@RequestMapping(value="/editPage/{pageId}")
	public String editPage(@PathVariable("pageId") String pageId,Map<String,Object>map){
		map.put("url", "updatePageDetails");
		map.put("submitButton","Upadte");
		Page page = bookDAOimpl.getPageDetails(pageId);
		map.put("Page", page);
		map.put("pageList", bookDAOimpl.getPages(page.getBookId()));
		//int attachementNo =myUtil.getDocumentNo(pageId);
		
		int attachementNo = (Integer) bookDAOimpl.getTotalNumberOfAttachedDocs(pageId);
		map.put("attachementNo",attachementNo+1);
		map.put("documentList", bookDAOimpl.getAttachedDocuments(pageId));
		return "homePage";
	}
	
	@RequestMapping(value="/updatePageDetails")
	public String updatepageDetails(@ModelAttribute("Page")Page page,Map<String, Object> map){
		bookDAOimpl.updatePageDetails(page);
		map.put("url", "updatePageDetails");
		map.put("submitButton","Upadte");
		map.put("Page", bookDAOimpl.getPageDetails(page.getPageId()));
		map.put("pageList", bookDAOimpl.getPages(page.getBookId()));
		//int attachementNo = myUtil.getDocumentNo(page.getPageId());
		int attachementNo = (Integer) bookDAOimpl.getTotalNumberOfAttachedDocs(page.getPageId());
		map.put("attachementNo",attachementNo+1);
		map.put("documentList", bookDAOimpl.getAttachedDocuments(page.getPageId()));
		return "homePage";
	}
	@RequestMapping(value="/addDocumet")
	public String addDocument(@ModelAttribute("Attachement") Attachement doc,@Validated Attachement bookValid,@RequestParam("attachedFile") MultipartFile content,Map<String, Object> map){
		doc.setAttachementId(IDGenerater.idGenerater(100000000));
		
		try {
			if(!content.isEmpty()) {
				doc.setContent(bookDAOimpl.getBlob(content.getBytes()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookDAOimpl.addDocument(doc);
		map.put("url", "updatePageDetails");
		map.put("submitButton","Upadate");
		Page page = bookDAOimpl.getPageDetails(doc.getPageId());
		map.put("Page",page);
		map.put("pageList",bookDAOimpl.getPages(page.getBookId()));
		//int attachementNo = myUtil.getDocumentNo(page.getPageId());
		int attachementNo = (Integer) bookDAOimpl.getTotalNumberOfAttachedDocs(page.getPageId());
		map.put("attachementNo",attachementNo+1);
		map.put("documentList", bookDAOimpl.getAttachedDocuments(page.getPageId()));
		return "homePage";
	}
	   @RequestMapping("/downloadAttachment/{attachementNo}")
	    public String downloadBookPhotograph(@PathVariable("attachementNo") String attachementNo, HttpServletResponse response) {
	        Attachement doc  = bookDAOimpl.getAttachment(attachementNo);
	        try {
	            if (doc.getContent()!=null) {
	                response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getTitle()+ "\"");
	                OutputStream out = response.getOutputStream();
	               /* response.setContentType("image/gif");
	                response.setContentType("image/jpg");*/
	               /* response.setContentType("text/html");*/
	                IOUtils.copy(doc.getContent().getBinaryStream(), out);
	                out.flush();
	                out.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return null;
	    }
	   
	   
	   @RequestMapping(value="/admin/test",method=RequestMethod.GET)
		public String adminPage(){
			logger.info("Initialized BookDaoImpl auto wired component ");
			return "admin";
		}
}
