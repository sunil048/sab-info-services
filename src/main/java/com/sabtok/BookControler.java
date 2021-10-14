package com.sabtok;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.BookDao;
import com.sabtok.persistance.dao.EventDao;
import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Event;
import com.sabtok.persistance.entity.EventAction;
import com.sabtok.persistance.entity.Page;
import com.sabtok.util.SabInfoUtil;

@RestController
@RequestMapping("/book")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao","com.sabtok.util.*"})
@CrossOrigin(origins = "http://localhost:4200")
@ControllerAdvice
public class BookControler {
	
	Logger log = Logger.getLogger(BookControler.class);
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	SabInfoUtil util;

	@GetMapping("/booksCount")
	public Integer bookNumber() {
		System.out.println("calling book number method");
		return (int) bookDao.count();
	}
	
	@GetMapping("/all")
	public List<Book> getBooks(){
		log.info("Getting list of books");
		return bookDao.findByOrderByBookNameAsc();
	}
	
	@GetMapping("/details/{bookId}")
	public Book getBookDetails(@PathVariable String bookId){
		return bookDao.findOne(bookId);
	}
	
	
	
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	@ResponseBody
	public Integer saveBook(@Valid @RequestBody Book book) {
		log.info("calling method and saving entity ");
		System.out.println(book);
		
		Event log = new Event();
		log.setBookId(book.getBookId());
		log.setAction(EventAction.CREATED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		return book.getBookNo();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updateBook(@Valid @RequestBody Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		Event log = new Event();
		log.setBookId(book.getBookId());
		log.setAction(EventAction.MODIFIED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		return EventAction.MODIFIED+" book "+book.getBookId();
	}
	/*
	@GetMapping("/id/{bookNo}")
	public Book getBookDetails(@PathVariable Long bookNo){
		return bookDao.findOne(bookNo);
	}
	

	@GetMapping("/no/{bookNo}")
	public ResponseEntity<Book> getBookDetailsByBookId(@PathVariable("bookNo") String bookNo){
		log.info("getBookDetailsByBookId "+bookNo);
		System.out.println("hello");
		Book book= bookDao.findOne(Long.valueOf(bookNo));
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/{bookNumber}",method=RequestMethod.GET)
	@ResponseBody
	public String deleteBook(@PathVariable("bookNumber") Long bookNo) {
		System.out.println(String.format("Deleting book number "+ bookNo));
		Event log = new Event();
		log.setBookId(String.valueOf(bookNo));
		log.setAction(EventAction.DELETED);
	    util.updateLogFields(log); 
		bookDao.delete(Long.valueOf(bookNo));
		return EventAction.DELETED+" book "+bookNo;
	}
	
		*/
}
