package com.sabtok.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.Book;


@Repository
public interface BookDao extends JpaRepository<Book, String>{

	List<Book> findByOrderByBookNameAsc();
}
