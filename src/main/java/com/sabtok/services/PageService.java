package com.sabtok.services;

import java.util.List;

import com.sabtok.entity.Page;
public interface PageService {
  
	public Long getPageCount();
	public Integer getPageCountForBook(String bookId);
	public Page getPageDetails(String pageId);
	public int creatPage(Page page);
	public String updatePage(Page page);
	public Page getPageDetailsByBookNo(Long pageNo);
	public List<Page> getPageListByBookId(String bookId);
	public List<Page> getAllPagesList();
	
}
