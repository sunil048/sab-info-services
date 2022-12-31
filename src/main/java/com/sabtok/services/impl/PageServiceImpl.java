package com.sabtok.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sabtok.dao.PageDao;
import com.sabtok.entity.EventAction;
import com.sabtok.entity.Page;
import com.sabtok.services.PageService;
import com.sabtok.util.SabInfoUtil;
import com.sabtok.util.StringDateConverter;

@Service
public class PageServiceImpl implements PageService{

	Logger log = LoggerFactory.getLogger(PageServiceImpl.class);
	
	@Autowired
    PageDao pageRepo;
	
	@Autowired
	SabInfoUtil util;
	
	@Override
	public Long getPageCount() {
		return pageRepo.count();
	}

	@Override
	public Integer getPageCountForBook(String bookId) {
		return pageRepo.getPageListByBookId(bookId).size();
	}

	@Override
	public Page getPageDetails(String pageId) {
		return pageRepo.getPageDetailsByPageId(pageId);
	}

	@Override
	public int creatPage(Page page) {
		//Event log = new Event();
				//log.setPageId(page.getPageId());
				//log.setAction(EventAction.CREATED);
				//util.updateLogFields(log);
				page.setCreatedDate(StringDateConverter.getTimeStamp());
				pageRepo.save(page);
				return page.getPageNo();
	}

	@Override
	public String updatePage(Page page) {
		//Event log = new Event();
				//log.setPageId(page.getPageId());
				//log.setAction(EventAction.MODIFIED);
				//util.updateLogFields(log);
				pageRepo.save(page);
				return EventAction.MODIFIED+" Page "+page.getPageId();
	}

	@Override
	public Page getPageDetailsByBookNo(Long pageNo) {
		log.info("getPageDetailsByBookId "+pageNo);
		Optional<Page> page= pageRepo.findById(Long.valueOf(pageNo));
		return page.get();
	}

	@Override
	public List<Page> getPageListByBookId(String bookId) {
		log.info("Getting page List for given book id "+bookId);
		return  pageRepo.getPageListByBookIdOrderByPageNoAsc(bookId);
	}

	@Override
	public List<Page> getAllPagesList() {
		return pageRepo.findAll();
	}

}
