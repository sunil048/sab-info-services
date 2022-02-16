package com.sabtok;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.ProjectJobRunnerThread;
import com.sabtok.report.TasktJobRunnerThread;
import com.sabtok.util.RestConfig;

@Component
public class JobRunner {

	private static final int MAX_T = 8;

	MangoDAO mangoDAO = new MangoDAO();
	
	  ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
	
	public void processProjectJobs() {
		 mangoDAO.setDbName("reports");
		 mangoDAO.setCollectionName("projectjobs");
		 Document filter = new Document();
		 List<Document> projectJobs = mangoDAO.findByQuery(filter);
		 for (Document projJob : projectJobs ) {
			// Thread t = new Thread(new ProjectJobRunnerThread(mangoDAO, RestConfig.getTemplate(), projJob));
			// t.start();
			 pool.execute(new ProjectJobRunnerThread(mangoDAO, RestConfig.getTemplate(), projJob));
		 }
	}
	
	
	public void processTaskJobs(){
		mangoDAO.setDbName("reports");
		 mangoDAO.setCollectionName("taskjobs");
				 Document filter1 = new Document();
				 List<Document> tasktJobs = mangoDAO.findByQuery(filter1);
				 for (Document tskJob : tasktJobs ) {
					// Thread t = new Thread(new TasktJobRunnerThread(mangoDAO, RestConfig.getTemplate(), tskJob));
					// t.start();
					 pool.execute(new TasktJobRunnerThread(mangoDAO, RestConfig.getTemplate(), tskJob));
				 }
	}
	
	
}
