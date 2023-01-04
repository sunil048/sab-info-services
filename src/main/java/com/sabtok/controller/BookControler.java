 	package com.sabtok.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

//import javax.validation.Valid;

import org.slf4j.LoggerFactory;
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

import com.sabtok.entity.Book;
import com.sabtok.services.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
@Slf4j
public class BookControler {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/booksCount")
	public ResponseEntity<Object> bookNumber() {
		log.info("bookNumber() called.....");
		try {
			Long bookNo = bookService.bookNumber();
			return new ResponseEntity<Object>(bookNo,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error("Error while getting book no ",e);
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> getBooks(){
		log.info("getBooks() called.....");
		try {
			List<Book> bookList =  bookService.getBooks();
			return new ResponseEntity<Object>(bookList,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error("Error while getting books ",e);
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/details/{bookId}")
	public Book getBookDetails(@PathVariable String bookId){
		log.info("getBookDetails() called.....");
 		return Optional.ofNullable(bookService.getBookDetails(bookId)).get()
        .orElseThrow(IllegalArgumentException::new);
 		
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Book saveBook(@RequestBody Book book) {
	//public Book saveBook(@Valid @RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateBook( @RequestBody Book book) {
	//public String updateBook(@Valid @RequestBody Book book) {
		return bookService.updateBook(book);
	}
}
