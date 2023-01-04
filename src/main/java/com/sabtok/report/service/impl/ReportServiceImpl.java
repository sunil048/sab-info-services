package com.sabtok.report.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.dao.BookDao;
import com.sabtok.dao.PageDao;
import com.sabtok.entity.Book;
import com.sabtok.entity.Page;
import com.sabtok.report.service.ReportService;

@Component

public class ReportServiceImpl implements ReportService {

	@Autowired
	private PageDao pageDao;
	
	@Override
	public Page getPageDetails(String pageId) {
		return pageDao.findByPageId(pageId);
	}

}
