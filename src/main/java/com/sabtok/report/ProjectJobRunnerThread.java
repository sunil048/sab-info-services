package com.sabtok.report;

import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.PlmException;
import com.mongodb.BasicDBObject;
import com.sabtok.JobRunner;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.util.JsonUtil;

public class ProjectJobRunnerThread extends JobRunner implements Runnable{

	Logger log = LoggerFactory.getLogger(ProjectJobRunnerThread.class);
	
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
		log.info("Trigered the thread "+Thread.currentThread().getName());
		processProjectJobs();
		log.info("Completed thread "+Thread.currentThread().getName());
	}
	
	public void processProjectJobs(){
		log.debug(Thread.currentThread().getName()+" Processing project job "+doc.getString("JOB_ID"));
		boolean update = false;
		ReportData reportData = new ReportData();
		if (! doc.containsKey("PROJECT_DATA")) {
			String url = projectBaseURL+"/"+doc.getString("PROJECT_ID");
			//System.out.println(url);
			log.debug(Thread.currentThread().getName()+url);
			ResponseEntity<String> response  = restTemplate.getForEntity(url , String.class);
			String json = response.getBody();
			//System.out.println(json);
			doc.append("PROJECT_DATA", new Document((Map<String, Object>) JsonUtil.converStringToMap(json)));
			log.debug(Thread.currentThread().getName()+" Adding PROJECT_DATA");
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
			log.debug(Thread.currentThread().getName()+" Creating task job for the project report "+reportData.taskjobId);
			Thread  t = new Thread(new TaskJobCreatorThread(reportData,  mangoDAO));
			t.start();
		}
		if (! doc.containsKey("EXCEPTION_JOB_ID")) {
			 reportData = new ReportData();
			reportData.status = "DRAFT"; 
			reportData.component = doc.getString("COMPONENT"); 
			reportData.sub_component = doc.getString("SUB_COMPONENT"); 
			reportData.reportId = doc.getString("REPORT_ID");
			reportData.reportname = doc.getString("REPORT_NAME");
			reportData.createdDate = ReportUtil.getTimeStamp();
			reportData.modifiedDate =  ReportUtil.getTimeStamp();
			reportData.exceptionjobId = "JOB"+String.valueOf(ReportUtil.getJobId());
			reportData.modifiedBy = "PROJECT_JOB_RUNNER_THREAD";
			reportData.inputData= doc.getString("INPUT_DATA");
			reportData.createdBy= "PROJECT_JOB_RUNNER_THREAD";
			doc.append("EXCEPTION_JOB_ID", reportData.exceptionjobId);
			update = true;
			log.debug(Thread.currentThread().getName()+" Creating task job for the project report "+reportData.taskjobId);
			Thread  t = new Thread(new ExceptionJobCreatorThread(reportData,  mangoDAO));
			t.start();
		}
		try {
			if (update) {
				BasicDBObject updateQuery = new BasicDBObject();
				BasicDBObject updateQuery1 = new BasicDBObject();
				updateQuery1.append("PROJECT_DATA", doc.get("PROJECT_DATA"));
				updateQuery1.append("TASK_JOB_ID", doc.getString("TASK_JOB_ID"));
				updateQuery.append("$set",updateQuery1);
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.append("_id", doc.get("_id"));
				mangoDAO.setCollectionName("projectjobs");
				mangoDAO.update(searchQuery,updateQuery);
				log.debug(Thread.currentThread().getName()+" updated project job doc");
			}
			
		} catch (PlmException e) {
			log.error(Thread.currentThread().getName(),e);
			e.printStackTrace();
		}
	}

}
