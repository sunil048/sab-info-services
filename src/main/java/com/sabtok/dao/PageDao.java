package com.sabtok.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.Page;


@Repository
public interface PageDao extends JpaRepository<Page, Long>{

	public Page getPageDetailsByPageId(String pageId);
	public List<Page> getPageListByBookId(String bookId);
	public List<Page> getPageListByBookIdOrderByPageNoAsc(String bookId);
	
	@Query("select content from Page where pageid =?1")
	public String getContenttByPageId(String pageId);
	
}
