package com.sabtok.report.writer;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.DocumentException;
import com.sabtok.persistance.mongo.MangoDAO;
import com.sabtok.report.exception.ReportGenerateException;

@Component
public class ReportWriter {

	@Autowired
	private MangoDAO mangoDao;
	
	@Autowired
	TaskWriter taskWriter;
	
	@Autowired
	ProjectWriter projectWriter;
	
	public void taskReportWriter(HttpServletResponse response, String reportId, String component) throws ReportGenerateException {
		mangoDao.setDbName("reports");
		if (component.equals("TASK"))
			mangoDao.setCollectionName("taskjobs");
		else
			mangoDao.setCollectionName("projectjobs"); 
		Document filter = new Document();
		//filter.append("STATUS", "WRITER");
		filter.append("REPORT_ID", reportId);
		List<Document>docList = mangoDao.findByQuery(filter);
		if (docList.isEmpty())
			throw new ReportGenerateException("task List is empty");
		try {
			if (component.equals("TASK"))
					taskWriter.export(response,docList.get(0));
			else {
				String taskJobId = docList.get(0).getString("TASK_JOB_ID");
				mangoDao.setCollectionName("taskjobs");
				Document jobfilter = new Document();
				jobfilter.append("REPORT_ID", reportId);
				jobfilter.append("JOB_ID", taskJobId);
				List<Document> taskJobdoc = mangoDao.findByQuery(jobfilter);
				if (taskJobdoc.isEmpty())
					throw new ReportGenerateException("task List is empty");
				projectWriter.export(response, docList.get(0),taskJobdoc.get(0));
			}
				
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
