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
import com.sabtok.entity.PageEventAction;
import com.sabtok.entity.Page;
import com.sabtok.services.PageService;
import com.sabtok.util.SabInfoUtil;
import com.sabtok.util.StringDateConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PageServiceImpl implements PageService{

	@Autowired
    private PageDao pageRepo;
	
	@Autowired
	private PageActivityServiceImpl pageActivityServiceImpl;
	
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
		log.debug("Creating page");
		page.setCreatedDate(StringDateConverter.getTimeStamp());
		Page pageObj = pageRepo.save(page);
		log.debug("Creating created");
		if (pageObj != null) {
			pageActivityServiceImpl.logPageActivity(page,PageEventAction.CREATE);
		}
		return page.getPageNo();
	}

	@Override
	public String updatePage(Page page) {
		log.debug("updatePage() called...");
		String oldContenet = pageRepo.getContenttByPageId(page.getPageId()); 
		Page pageObj = pageRepo.save(page);
		if (pageObj != null) {
			log.debug("Recording page activity...");
			pageActivityServiceImpl.logPageActivity(page,PageEventAction.UPDATE,oldContenet);
		}
		log.debug("updatePage() completed...");
		return page.getPageId();
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
