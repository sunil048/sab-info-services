package com.sabtok;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.BookDao;
import com.sabtok.persistance.dao.LogDao;
import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Log;
import com.sabtok.persistance.entity.LogAction;
import com.sabtok.util.SabInfoUtil;

@RestController
@RequestMapping("/book")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao","com.sabtok.util.*"})
public class BookControler {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	SabInfoUtil util;

	@GetMapping("/booksCount")
	public Integer bookNumber() {
		System.out.println("calling book number method");
		return (int) bookDao.count();
	}
	
	@GetMapping("/books/all")
	public List<Book> getBooks(){
		return bookDao.findAll();
	}
	
	@GetMapping("/books/{bookNo}")
	public Book getBookDetails(@PathVariable Long bookNo){
		return bookDao.findOne(bookNo);
	}
	
	@GetMapping("/books/id/{bookId}")
	public Book getBookDetailsByBookId(@PathVariable Long bookNo){
		return bookDao.findOne(bookNo);
	}
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	@ResponseBody
	public Long saveBook(@Valid @RequestBody Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		
		Log log = new Log();
		log.setBookId(book.getBookId());
		log.setAction(LogAction.CREATED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		return book.getBookNo();
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updateBook(@Valid @RequestBody Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		Log log = new Log();
		log.setBookId(book.getBookId());
		log.setAction(LogAction.MODIFIED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		return LogAction.MODIFIED+" book "+book.getBookId();
	}
	
		
}
