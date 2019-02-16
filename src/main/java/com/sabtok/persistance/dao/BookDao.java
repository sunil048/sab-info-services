package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Book;

public interface BookDao extends JpaRepository<Book, Long>{

}
