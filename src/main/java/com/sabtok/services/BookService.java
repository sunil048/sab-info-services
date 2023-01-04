package com.sabtok.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sabtok.entity.Book;

@Service
public interface BookService {

	public Book saveBook(Book book);
	public String updateBook(Book book);
	public Long bookNumber();
	public List<Book> getBooks();
	public Optional<Book> getBookDetails(String bookId);
	
}
