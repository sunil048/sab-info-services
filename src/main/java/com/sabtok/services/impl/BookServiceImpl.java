package com.sabtok.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabtok.BookControler;
import com.sabtok.persistance.dao.BookDao;
import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Event;
import com.sabtok.persistance.entity.EventAction;
import com.sabtok.services.BookService;
import com.sabtok.util.SabInfoUtil;

@Service
public class BookServiceImpl implements BookService{

	Logger log = Logger.getLogger(BookControler.class);
	
	BookDao bookDao;
	
	@Autowired
	SabInfoUtil util;
	
	@Override
	public Book saveBook(Book book) {
		log.info("calling method and saving entity ");
		System.out.println(book);
		Event log = new Event();
		log.setBookId(book.getBookId());
		log.setAction(EventAction.CREATED);
	    util.updateLogFields(log); 
		return bookDao.save(book);
	}

	@Override
	public String updateBook(Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		Event log = new Event();
		log.setBookId(book.getBookId());
		log.setAction(EventAction.MODIFIED);
	    util.updateLogFields(log); 
		bookDao.save(book);
		return EventAction.MODIFIED+" book "+book.getBookId();
	}

	@Override
	public Integer bookNumber() {
		System.out.println("calling book number method");
		return (int) bookDao.count();
	}

	@Override
	public List<Book> getBooks() {
		log.info("Getting list of books");
		return bookDao.findByOrderByBookNameAsc();
	}

	@Override
	public Book getBookDetails(String bookId) {
		return bookDao.findOne(bookId);
	}

}
