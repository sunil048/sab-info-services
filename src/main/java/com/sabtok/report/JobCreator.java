package com.sabtok.report;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.PlmException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sabtok.ReportController;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.util.RestCalls;
import com.sabtok.util.RestConfig;

@Component
public class JobCreator {

	Logger log = LoggerFactory.getLogger(JobCreator.class);
	// Maximum number of threads in thread pool
    static final int MAX_T = 10;
	
	@Autowired
	@Qualifier("secDB")
	private DataSource datsource;
	
	@Autowired
	private MangoDAO mangoDAO;
	
	public void triggerJobCreator() {
		 
		log.debug("Creating jobs.");
		Connection con;
		try {
			// creates a thread pool with MAX_T no. of 
	        // threads as the fixed pool size(Step 2)
	        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
			con = datsource.getConnection();
			ResultSet rs =	con.createStatement().executeQuery("select * from reports");
			while (rs.next()) {
				ReportData reportData = new ReportData();
				if (rs.getString("STATUS").equals("DRAFT")) {
					reportData.status = "DRAFT"; 
					reportData.component = rs.getString("component"); 
					reportData.sub_component = rs.getString("sub_component"); 
					reportData.reportId = rs.getString("report_Id");
					reportData.reportname = rs.getString("reportname");
					reportData.createdDate = rs.getString("created_date");
					reportData.createdBy ="JOB_CREATER";
					reportData.inputData= rs.getString("input_data");
					System.out.println(reportData.component+" : "+reportData.sub_component);
					if (reportData.component.equals("PROJECT")) {
						log.debug("Creating project job for the report "+reportData.reportId);
						//Thread t1 = new Thread(new ProjectJobCreatorThread(reportData,mangoDAO));
						//t1.start();
						pool.execute(new ProjectJobCreatorThread(reportData,mangoDAO));
					}
					if (reportData.component.equals("TASK")) {
						log.debug("Creating task job for the report "+reportData.reportId);
						//Thread  t = new Thread(new TaskJobCreatorThread(reportData, mangoDAO));
						//t.start();
						pool.execute(new TaskJobCreatorThread(reportData, mangoDAO));
					}
				}
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * public List<ReportData> getReportList(){ Connection con; List<ReportData>
	 * reports = new ArrayList<>(); try { con = datsource.getConnection(); ResultSet
	 * rs = con.createStatement().executeQuery("select * from Report_Status");
	 * ReportData reportData; while (rs.next()) { reportData = new ReportData();
	 * reportData.status = "DRAFT"; reportData.component =
	 * rs.getString("COMPONENT"); reportData.sub_component =
	 * rs.getString("SUB_COMPONENT"); reportData.reportId =
	 * rs.getString("REPORT_ID"); reportData.reportname =
	 * rs.getString("REPORT_NAME"); reportData.createdDate =
	 * rs.getString("TRIGGER_DATE"); reports.add(reportData); }
	 * System.out.println(reports); rs.close(); con.close(); return reports;
	 * 
	 * }catch (Exception e) { // TODO: handle exception } return reports; }
	 */
		
	
}
