package com.sabtok;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.BookDao;
import com.sabtok.persistance.entity.Book;

@RestController
@RequestMapping("/book")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao"})
public class BookControler {
	
	@Autowired
	BookDao bookDao;

	@GetMapping("/booksCount")
	public Integer bookNumber() {
		System.out.println("calling book number method");
		return (int) bookDao.count();
	}
	
	@GetMapping("/books/all")
	public List<Book> getBooks(){
		return bookDao.findAll();
	}
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	@ResponseBody
	public String saveBook(@Valid @RequestBody Book book) {
		System.out.println("calling method and saving entity");
		System.out.println(book);
		bookDao.save(book);
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
