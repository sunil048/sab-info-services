package com.sabtok.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.persistance.dao.EventDao;
import com.sabtok.persistance.entity.Event;
import com.sabtok.persistance.entity.EventAction;

@Component
public class SabInfoUtil {

	
	EventDao logDao;
	public void updateLogFields(Event log) {
		switch(log.getAction()) {
		
		case CREATED :
			log.setCreatedBy(ServicesConstatnt.DEFAULT_USER);
			log.setCreatedDate(java.sql.Date.valueOf(LocalDate.now()));
			break;
		case MODIFIED :
			log.setModifiedBy(ServicesConstatnt.DEFAULT_USER);
			log.setLastModifiedDate((java.sql.Date.valueOf(LocalDate.now())));
			break;	
		}
		logDao.save(log);
	}
}
