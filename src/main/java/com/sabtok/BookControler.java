package com.sabtok;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.entity.Book;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookControler {
	
	Logger log = Logger.getLogger(BookControler.class);
	
	@Autowired
	com.sabtok.services.BookService bookService;
	
	@GetMapping("/booksCount")
	public ResponseEntity<Integer> bookNumber() {
		System.out.println("calling book number method");
		Integer bookNo = bookService.bookNumber();
		return new ResponseEntity(HttpStatus.ACCEPTED).ok(bookNo);
	}
	
	@GetMapping("/all")
	public List<Book> getBooks(){
		return bookService.getBooks();
	}
	
	@GetMapping("/details/{bookId}")
	public Book getBookDetails(@PathVariable String bookId){
		return bookService.getBookDetails(bookId);
	}
	
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	public Book saveBook(@Valid @RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updateBook(@Valid @RequestBody Book book) {
		return bookService.updateBook(book);
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
