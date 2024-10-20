package com.sabtok.services;

import java.util.List;
import java.util.Set;

import com.sabtok.entity.LinkageStatus;
import com.sabtok.entity.LinkageType;
import com.sabtok.entity.Page;
import com.sabtok.entity.PageLinkage;

public interface PageService {
  
	public Long getPageCount();
	public Integer getPageCountForBook(String bookId);
	public Page getPageDetails(String pageId);
	public int creatPage(Page page);
	public String updatePage(Page page, Boolean backUpFlag);
	public Page getPageDetailsByBookNo(Long pageNo);
	public List<Page> getPageListByBookId(String bookId);
	public List<Page> getAllPagesList();
	public String deletePage(String pageId);
	public PageLinkage linkPage(String pageId, String itemId, LinkageType linkageType);
	List<PageLinkage> getPageLinkedItems(String pageId);
	List<PageLinkage> getAllNotProceessedLinks();

	public Object updateLinks(Set<PageLinkage> links);
	
}
