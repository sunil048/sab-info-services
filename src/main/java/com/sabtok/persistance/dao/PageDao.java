package com.sabtok.persistance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Page;

public interface PageDao extends JpaRepository<Page, Long>{

	public Page getPageDetailsByPageId(String pageId);
	public List<Page> getPageListByBookId(String bookId);
}
