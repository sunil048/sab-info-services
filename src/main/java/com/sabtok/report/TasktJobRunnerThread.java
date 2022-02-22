package com.sabtok.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.PlmException;
import com.mongodb.BasicDBObject;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.util.JsonUtil;

public class TasktJobRunnerThread implements Runnable{

	Logger log = LoggerFactory.getLogger(ProjectJobRunnerThread.class);
	
	MangoDAO mangoDAO ;
	RestTemplate restTemplate;
	private String taskBaseURL="http://localhost:8080/sab-plm-services-2.4/task/list";
	private String projectBaseURL="http://localhost:8080/sab-plm-services-2.4/project/details";
	Document doc;
	
	public TasktJobRunnerThread(MangoDAO mangoDAO, RestTemplate restTemplate, Document doc) {
		super();
		this.mangoDAO = mangoDAO;
		this.restTemplate = restTemplate;
		this.doc = doc;
	}

	@Override
	public void run() {
		log.info("Trigered the thread "+Thread.currentThread().getName());
		processTaskJobs();		
		log.info("Completed thread "+Thread.currentThread().getName());
		
	}
	public void processTaskJobs(){
		log.debug(Thread.currentThread().getName()+"Processing task job "+doc.getString("JOB_ID"));
		boolean update = false;
		List<Document> taskDocList = new ArrayList();
		if (!doc.containsKey("TASK_DATA")) {
			  //String url = taskBaseURL+"/"+doc.getString("SUB_COMPOENENT");
			  String url = taskBaseURL;
			  System.out.println(url);
			  if (doc.getString("COMPONENT").equals("TASK")) {
				  ResponseEntity<String> response  = restTemplate.getForEntity(url , String.class);
				  String json = response.getBody();
				  List<Object> taskList =  (List<Object>) JsonUtil.converStringToObject(json,List.class);
				   Document taskDoc = new Document();
			       for (Object task : taskList) {
			        System.out.println(task);
					taskDoc = new Document((Map<String, Object>) task);
					taskDocList.add(taskDoc);
				   }
			      // System.out.println(taskDocList);
				doc.append("TASK_DATA", taskDocList);
				log.debug(Thread.currentThread().getName()+" Adding TASK_DATA ");
				update = true;
			  }
			  if (doc.getString("COMPONENT").equals("PROJECT")) {
				  taskDocList.clear();
				  String projectId = doc.getString("INPUT_DATA");
					String purl = projectBaseURL+"/"+projectId;
					ResponseEntity<String> response  = restTemplate.getForEntity(purl , String.class);
					String json = response.getBody();
					System.out.println(json);
					Map <String,Object> projectData = (Map<String, Object>) JsonUtil.converStringToMap(json);
					String projectName = (String) projectData.get("projectName");
					
					 ResponseEntity<String> response1  = restTemplate.getForEntity(url , String.class);
					  String json1 = response1.getBody();
					  List<Object> taskList =  (List<Object>) JsonUtil.converStringToObject(json1,List.class);
					   Document taskDoc = new Document();
				       for (Object task : taskList) {
				        System.out.println(task);
						taskDoc = new Document((Map<String, Object>) task);
						if (taskDoc.getString("projectName") != null && taskDoc.getString("projectName").equals(projectName))
							taskDocList.add(taskDoc);
					   }
				       log.debug(Thread.currentThread().getName()+" Adding TASK_DATA of the project tasks "+projectId+" "+projectName);
				       update = true;
			  }
		 try {
			 if (update) {
				 
				 BasicDBObject updateQuery1 = new BasicDBObject();
					updateQuery1.append("TASK_DATA", taskDocList);
					
					BasicDBObject updateQuery = new BasicDBObject();
					updateQuery.append("$set",updateQuery1);
					
					BasicDBObject searchQuery = new BasicDBObject();
					searchQuery.append("_id", doc.get("_id"));   
					
					mangoDAO.setCollectionName("taskjobs");
					mangoDAO.update(searchQuery, updateQuery);
					log.debug(Thread.currentThread().getName()+"updated task job doc");
			 }
			} catch (PlmException e) {
				log.error(Thread.currentThread().getName(),e);
				e.printStackTrace();
			}
		}
	}
	
	
	
}
