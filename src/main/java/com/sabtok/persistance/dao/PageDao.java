package com.sabtok.persistance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Page;

@Repository
public interface PageDao extends JpaRepository<Page, Long>{

	public Page getPageDetailsByPageId(String pageId);
	public List<Page> getPageListByBookId(String bookId);
	public List<Page> getPageListByBookIdOrderByPageNoAsc(String bookId);
	
}
