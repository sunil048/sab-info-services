package com.sabtok.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.sql.DataSource;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.PlmException;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.service.ReportHistory;
import com.sabtok.util.IDGenerator;
import com.sabtok.util.JsonUtil;
import com.sabtok.util.RestCalls;


public class ProjectJobCreatorThread implements Runnable {

	private MangoDAO mangoDAO;
	private ReportData reportData;
	
	ProjectJobCreatorThread(ReportData reportData,  MangoDAO mangoDAO){
		this.reportData = reportData;
		this.mangoDAO  = mangoDAO;
	}

	@Override
	public void run() {
			try {
				projectJobCreator(reportData);
				Thread.sleep(10000);
			} catch (PlmException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void projectJobCreator(ReportData reportData) throws PlmException {
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
	   } else {
		   System.out.println("Project job already created.");
	   }
	   
	}


}
