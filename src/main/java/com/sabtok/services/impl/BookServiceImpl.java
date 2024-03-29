package com.sabtok.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sabtok.dao.BookDao;
import com.sabtok.entity.Book;
import com.sabtok.entity.Event;
import com.sabtok.entity.PageEventAction;
import com.sabtok.services.BookService;
import com.sabtok.util.SabInfoUtil;

@Component
public class BookServiceImpl implements BookService {

	Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	SabInfoUtil util;
	
	@Override
	public Book saveBook(Book book) {
		log.info("calling method and saving entity ");
		System.out.println(book);
		Event log = new Event();
		log.setBookId(book.getBookId());
		//log.setAction(PageEventAction.CREATED);
	    util.updateLogFields(log); 
		return bookDao.save(book);
	}

	@Override
	public String updateBook(Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		Event log = new Event();
		log.setBookId(book.getBookId());
		//log.setAction(PageEventAction.MODIFIED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		//return PageEventAction.MODIFIED+" book "+book.getBookId();
		return null;
	}

	@Override
	public Long bookNumber() {
		System.out.println("calling book number method");
		Long bookNo = bookDao.count();
		return bookNo;
	}

	@Override
	public List<Book> getBooks() {
		log.info("Getting list of books");
		return bookDao.findByOrderByBookNameAsc();
	}

	@Override
	public Optional<Book> getBookDetails(String bookId) {
		System.out.println("service invoked");
		Optional<Book> book =  bookDao.findById(bookId);
		return book;
	}

}
