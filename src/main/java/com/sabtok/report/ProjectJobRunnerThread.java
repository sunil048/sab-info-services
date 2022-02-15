package com.sabtok.report;

import java.util.List;

import javax.sql.DataSource;

import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.PlmException;
import com.mongodb.BasicDBObject;
import com.sabtok.JobRunner;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.util.JsonUtil;
import com.sabtok.util.RestConfig;

public class ProjectJobRunnerThread extends JobRunner implements Runnable{

	MangoDAO mangoDAO ;
	RestTemplate restTemplate;
	private String projectBaseURL="http://localhost:8080/sab-plm-services-2.4/project/details";
	Document doc;
	
	public ProjectJobRunnerThread(MangoDAO mongoDao, RestTemplate restTemplate,Document job) {
		super();
		this.doc = job;
		this.mangoDAO = mongoDao;
		this.restTemplate = restTemplate;
	}


	@Override
	public void run() {
		processProjectJobs();
	}
	
	public void processProjectJobs(){
		boolean update = false;
		ReportData reportData = new ReportData();
		if (! doc.containsKey("PROJECT_DATA")) {
			String url = projectBaseURL+"/"+doc.getString("PROJECT_ID");
			System.out.println(url);
			ResponseEntity<String> response  = restTemplate.getForEntity(url , String.class);
			String json = response.getBody();
			System.out.println(json);
			doc.append("PROJECT_DATA", JsonUtil.converStringToMap(json));
			update = true;
		}
		if (! doc.containsKey("TASK_JOB_ID")) {
			 reportData = new ReportData();
			reportData.status = "DRAFT"; 
			reportData.component = doc.getString("COMPONENT"); 
			reportData.sub_component = doc.getString("SUB_COMPONENT"); 
			reportData.reportId = doc.getString("REPORT_ID");
			reportData.reportname = doc.getString("REPORT_NAME");
			reportData.createdDate = ReportUtil.getTimeStamp();
			reportData.modifiedDate =  ReportUtil.getTimeStamp();
			reportData.taskjobId = "JOB"+String.valueOf(ReportUtil.getJobId());
			reportData.modifiedBy = "PROJECT_JOB_RUNNER_THREAD";
			reportData.inputData= doc.getString("INPUT_DATA");
			reportData.createdBy= "PROJECT_JOB_RUNNER_THREAD";
			doc.append("TASK_JOB_ID", reportData.taskjobId);
			update = true;
			Thread  t = new Thread(new TaskJobCreatorThread(reportData,  mangoDAO));
			t.start();
		}
		try {
			if (update) {
				BasicDBObject updateQuery = new BasicDBObject();
				BasicDBObject updateQuery1 = new BasicDBObject();
				updateQuery1.append("PROJECT_DATA", doc.get("PROJECT_DATA"));
				updateQuery1.append("TASK_JOB_ID", doc.getString("TASK_JOB_ID"));
				updateQuery.append("$set",updateQuery1);
				//updateQuery.append("$set",new BasicDBObject().append("TASK_JOB_ID", doc.getString("TASK_JOB_ID")));
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.append("_id", doc.get("_id"));
				mangoDAO.setCollectionName("projectjobs");
				mangoDAO.update(searchQuery,updateQuery);
			}
			
		} catch (PlmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
