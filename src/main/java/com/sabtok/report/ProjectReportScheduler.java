package com.sabtok.report;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PlmException;
import com.mongodb.BasicDBObject;
import com.sabtok.persistance.mongo.MangoDAO;

@Component
public class ProjectReportScheduler {
	
	@Autowired
	private MangoDAO mangoDAO;
	
	public void init() {
		try {
			processAllProjectReports();
		} catch (PlmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAllProjectReports() throws PlmException {
		mangoDAO.setDbName("reports");
		mangoDAO.setCollectionName("project_reports");
		Document filter = new Document();
		filter.append("STATUS", "DRAFT");
		List<Document> projectReports = mangoDAO.findByQuery(filter);
		for (Document doc: projectReports) {
			if (doc.containsKey("TASK_JOB")) {
				
			} else {
				mangoDAO.setCollectionName("taskjobs");
				Document taskDoc = new Document();
				taskDoc.append("PROJECT_ID", doc.getString("PROJECT_ID"));
				taskDoc.append("COMPONENT", "ALL");
				taskDoc.append("STATUS", "DRAFT");
				taskDoc.append("CREATEDBY", "PROJECT_REPORT_SCHEDULER");
				taskDoc.append("CREATED_DATE", LocalDateTime.now().toString());
				mangoDAO.insertOne(taskDoc);
				Document taskJob = new Document();
				taskJob.append("STATUS", "DRAFT");
				doc.append("TASK_JOB", taskJob);
				doc.append("MODIFIED_DATE", LocalDateTime.now().toString());
				doc.append("MODIFIEDBY", "PROJECT_REPORT_SCHEDULER");
				mangoDAO.setCollectionName("project_reports");
				mangoDAO.insertOne(doc);
			}
		
		}
		System.out.println(projectReports);
		
	}
} 
