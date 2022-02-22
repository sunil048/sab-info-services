package com.sabtok.report;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.PlmException;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.service.ReportHistory;

public class ProjectJobCreatorThread implements Runnable {

	Logger log = LoggerFactory.getLogger(ProjectJobCreatorThread.class);
	
	private MangoDAO mangoDAO;
	private ReportData reportData;
	
	ProjectJobCreatorThread(ReportData reportData,  MangoDAO mangoDAO){
		this.reportData = reportData;
		this.mangoDAO  = mangoDAO;
	}

	@Override
	public void run() {
			try {
				log.info("Trigered the thread "+Thread.currentThread().getName());
				projectJobCreator(reportData);
				Thread.sleep(10000);
			} catch (PlmException | InterruptedException e) {
				log.error(Thread.currentThread().getName()+" Error while executing thread ",e);
			}
	}

	private void projectJobCreator(ReportData reportData) throws PlmException {
	   log.debug(Thread.currentThread().getName()+" connecting to mongo db");
	   mangoDAO.setDbName("reports");
	   mangoDAO.setCollectionName("projectjobs");
	   Document filter = new Document();
	   filter.append("REPORT_ID", reportData.reportId);
	   if (mangoDAO.findByQuery(filter).isEmpty()) {
		   Document doc = new Document();
		   doc.append("STATUS", "DRAFT");
		   doc.append("JOB_ID", "JOB"+ReportUtil.getJobId());
		   doc.append("DESCRIPTION", "Initiated trigger process");
		   doc.append("REPORT_ID", reportData.reportId);
		   doc.append("REPORT_NAME", reportData.reportname);
		   doc.append("SUB_COMPONENT", reportData.sub_component);
		   doc.append("INPUT_DATA", reportData.inputData);
		   doc.append("PROJECT_ID", reportData.inputData);
		   doc.append("COMPONENT", reportData.component);
		   doc.append("CREATED_DATE", ReportUtil.getTimeStamp());
		   doc.append("CREATED_BY", reportData.createdBy);
		   doc.append("MODIFIED_DATE", ReportUtil.getTimeStamp());
		   mangoDAO.setCollectionName("projectjobs");
		   mangoDAO.insertOne(doc);
		   ReportHistory.addLog(reportData.reportId, "DRAFT", "Created project job "+doc.getString("JOB_ID"));
		   log.info(Thread.currentThread().getName()+" project job created "+doc.getString("JOB_ID"));
	   } else {
		   log.info("Project job already created.");
	   }
	   
	}


}
