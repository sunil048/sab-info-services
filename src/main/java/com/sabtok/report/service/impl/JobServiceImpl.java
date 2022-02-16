package com.sabtok.report.service.impl;

import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.ProjectJobRunnerThread;
import com.sabtok.report.service.JobService;

@Component
public class JobServiceImpl implements JobService {

	Logger log = LoggerFactory.getLogger(ProjectJobRunnerThread.class);
	
	@Autowired
	MangoDAO mongodao;
	
	@Override
	public Object getJobList(String reportId) {
		log.info("Getting job list");
		mongodao.setDbName("reports");
		mongodao.setCollectionName("taskjobs");
		Document filter = new Document();
		filter.append("REPORT_ID", reportId);
		List <Document> jobList = mongodao.findByQuery(filter);
		return jobList;
	}

}
