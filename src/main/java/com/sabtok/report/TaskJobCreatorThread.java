package com.sabtok.report;

import java.time.LocalDateTime;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.PlmException;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.service.ReportHistory;

public class TaskJobCreatorThread implements Runnable{

	Logger log = LoggerFactory.getLogger(TaskJobCreatorThread.class);
	
	ReportData reportData;
	private MangoDAO mangoDAO;
	
	public TaskJobCreatorThread(ReportData reportData, MangoDAO mangoDAO) {
		super();
		this.reportData = reportData;
		this.mangoDAO = mangoDAO;
	}

	@Override
	public void run() {
		log.info("Trigered the thread "+Thread.currentThread().getName());
		createTaskJob();
		try {
			log.info("Sleeping thread "+Thread.currentThread().getName());
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log.error(Thread.currentThread().getName()+" Error while executing thread ",e);
			e.printStackTrace();
		}
	}
	
	public void createTaskJob() {
		log.debug(Thread.currentThread().getName()+" connecting to mongo db");
		   mangoDAO.setDbName("reports");
		   mangoDAO.setCollectionName("taskjobs");
		   Document filter = new Document();
		   filter.append("REPORT_ID", reportData.reportId);
		   if (mangoDAO.findByQuery(filter).isEmpty()) {
			   Document doc = new Document();
			   doc.append("STATUS", "DRAFT");
			   doc.append("DESCRIPTION", "Initiated trigger process");
			   if (reportData.taskjobId == null)
				   doc.append("JOB_ID", "JOB"+ReportUtil.getJobId());
			   else
				   doc.append("JOB_ID", reportData.taskjobId);
			   doc.append("REPORT_ID", reportData.reportId);
			   doc.append("REPORT_NAME", reportData.reportname);
			   doc.append("COMPONENT", reportData.component);
			   doc.append("SUB_COMPONENT", reportData.sub_component);
			   doc.append("INPUT_DATA", reportData.inputData);
			   doc.append("CREATED_DATE", reportData.createdDate);
			   doc.append("CREATED_BY", reportData.createdBy);
			   doc.append("MODIFIED_DATE", ReportUtil.getTimeStamp());
			   doc.append("MODIFIED_BY", reportData.modifiedBy);
				try {
					mangoDAO.setCollectionName("taskjobs");
					mangoDAO.insertOne(doc);
				} catch (PlmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ReportHistory.addLog(reportData.reportId, "DRFT", "Created Task job "+doc.getString("JOB_ID"));
				log.info(Thread.currentThread().getName()+" project job created "+doc.getString("JOB_ID"));  
		   } else {
			   log.info("Project job already created.");
		   }
	}
	
	
}
