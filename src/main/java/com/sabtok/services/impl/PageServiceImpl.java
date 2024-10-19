package com.sabtok.services.impl;

import java.util.*;

import com.sabtok.dao.PageLinkageDao;
import com.sabtok.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabtok.dao.PageDao;
import com.sabtok.services.PageService;
import com.sabtok.util.StringDateConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

	@Autowired
    private PageDao pageRepo;
	
	@Autowired
	private PageActivityServiceImpl pageActivityServiceImpl;

	@Autowired
	private PageLinkageDao pageLinkageDao;
	
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
	public String updatePage(Page page, Boolean backUpFlag) {
		log.debug("updatePage() called...");
		String oldContenet = pageRepo.getContenttByPageId(page.getPageId()); 
		Page pageObj = pageRepo.save(page);
		if (backUpFlag == true) {
			log.debug("Recording page activity...");
			pageActivityServiceImpl.logPageActivity(page,PageEventAction.UPDATE,oldContenet);
		}
		log.debug("updatePage() completed...");
		return page.getPageId();
	}

	@Override
	public Page getPageDetailsByBookNo(Long pageNo) {
		log.info("getPageDetailsByBookId "+pageNo);
		Optional<Page> page= pageRepo.findById(String.valueOf(pageNo));
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

	@Override
	public String deletePage(String pageId) {
		pageRepo.deleteById(pageId);
		return "";
	}

	@Override
	public PageLinkage linkPage(String pageId, String itemId, LinkageType linkageType) {
		PageLinkage link = PageLinkage.builder().itemId(itemId).linkageType(linkageType).status(LinkageStatus.PENDING).pageId(pageId).build();
		return pageLinkageDao.save(link);
	}

	@Override
	public List<PageLinkage> getPageLinkedItems(String pageId) {
		return pageLinkageDao.findAllByPageId(pageId);
	}

	@Override
	public List<PageLinkage> getAllNotProceessedLinks() {
		List<LinkageStatus> failedPendingLinks = Arrays.asList(LinkageStatus.PENDING,LinkageStatus.FAILED);
		return pageLinkageDao.findAllByStatusIn(failedPendingLinks);
	}

	@Override
	public Object updateLinks(Set<PageLinkage> links) {
		return pageLinkageDao.saveAll(links);
	}


}
