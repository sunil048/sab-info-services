package com.sabtok.report.service;

import org.bson.Document;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.util.StringDateConverter;

public class ReportHistory {
	
	private static MangoDAO mongoDao = new MangoDAO(); 
	
	public static void addLog(String reportId, String status,String message) {
		try {
			try {
				mongoDao.setDbName("reports");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mongoDao.setCollectionName("report_history");
		Document doc = new Document();
		doc.append("REPORT_ID", reportId);
		doc.append("STATUS", status);
		doc.append("MESSAGE", message);
		doc.append("CREATED_DATE", StringDateConverter.getTimeStamp());
	}
	
	public Object getReportHistory(String reportId) {
		mongoDao.setDbName("reports");
		mongoDao.setCollectionName("report_history");
		Document filter = new Document();
		filter.append("REPORT_ID", reportId);
		return mongoDao.findByQuery(filter);
	}
}
