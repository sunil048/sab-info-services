package com.sabtok.report;

import java.time.LocalDateTime;
import org.bson.Document;
import com.PlmException;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.service.ReportHistory;

public class TaskJobCreatorThread implements Runnable{

	ReportData reportData;
	private MangoDAO mangoDAO;
	
	public TaskJobCreatorThread(ReportData reportData, MangoDAO mangoDAO) {
		super();
		this.reportData = reportData;
		this.mangoDAO = mangoDAO;
	}

	@Override
	public void run() {
		createTaskJob();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createTaskJob() {
		   mangoDAO.setDbName("reports");
		   mangoDAO.setCollectionName("taskjobs");
		   Document filter = new Document();
		   filter.append("REPORT_ID", reportData.reportId);
		   if (mangoDAO.findByQuery(filter).isEmpty()) {
			   Document doc = new Document();
			   doc.append("STATUS", "DRAFT");
			   doc.append("DESCRIPTION", "Initiated trigger process");
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
				  
		   } else {
			   System.out.println("The task job already created.");
		   }
	}
	
	
}
