package com.sabtok.report.service.impl;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.service.JobService;

@Component
public class JobServiceImpl implements JobService {

	@Autowired
	MangoDAO mongodao;
	
	@Override
	public Object getJobList(String reportId) {
		mongodao.setDbName("reports");
		mongodao.setCollectionName("taskjobs");
		Document filter = new Document();
		filter.append("REPORT_ID", reportId);
		List <Document> jobList = mongodao.findByQuery(filter);
		
		return jobList;
	}

}
