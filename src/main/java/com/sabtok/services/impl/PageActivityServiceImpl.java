package com.sabtok.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.dao.PageActivityDao;
import com.sabtok.dao.PageDao;
import com.sabtok.entity.Attachement;
import com.sabtok.entity.Page;
import com.sabtok.entity.PageActivity;
import com.sabtok.entity.PageEventAction;
import com.sabtok.util.IDGenerator;
import com.sabtok.util.StringDateConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PageActivityServiceImpl {
    
	@Autowired
	private PageActivityDao pageActivityDao;
	
	private PageActivity pageActivity;
	
	public void logPageActivity(Attachement attachement, PageEventAction actionType) {
		log.debug("logging page activity");
		pageActivity = new PageActivity();
		pageActivity.setAction(actionType.toString());
		pageActivity.setActivityId(IDGenerator.getPageActivityGenerator());
		pageActivity.setDate(attachement.getCreatedDate());
		pageActivity.setPageId(attachement.getPageId());
		pageActivity.setOldContent("");
		pageActivity.setNewContent("Doccument Id "+attachement.getAttachementId());
		pageActivity.setUpdateCreatedBy("Admin");
		pageActivityDao.save(pageActivity);
	}
	
	public void logPageActivity(Page page, PageEventAction actionType) {
		log.debug("logging page activity");
		pageActivity = new PageActivity();
		pageActivity.setAction(actionType.toString());
		pageActivity.setActivityId(IDGenerator.getPageActivityGenerator());
		pageActivity.setDate(StringDateConverter.getTimeStamp());
		pageActivity.setPageId(page.getPageId());
		pageActivity.setOldContent("");
		pageActivity.setNewContent(page.getContent());
		pageActivity.setUpdateCreatedBy("Admin");
		pageActivityDao.save(pageActivity);
	}
	
	public void logPageActivity(Page page, PageEventAction actionType, String oldContent) {
		log.debug("logging page activity");
		pageActivity = new PageActivity();
		pageActivity.setAction(actionType.toString());
		pageActivity.setActivityId(IDGenerator.getPageActivityGenerator());
		pageActivity.setDate(StringDateConverter.getTimeStamp());
		pageActivity.setPageId(page.getPageId());
		pageActivity.setOldContent(oldContent);
		pageActivity.setNewContent(page.getContent());
		pageActivity.setUpdateCreatedBy("Admin");
		pageActivityDao.save(pageActivity);
	}
}
