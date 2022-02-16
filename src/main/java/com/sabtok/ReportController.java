package com.sabtok;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.entity.Report;
import com.sabtok.report.JobCreator;
import com.sabtok.report.ReportUtil;
import com.sabtok.report.exception.ReportGenerateException;
import com.sabtok.report.service.ReportHistory;
import com.sabtok.report.writer.ReportWriter;
import com.sabtok.services.ReportService;


@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

	Logger log = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	JobCreator jobCreator;
	
	@Autowired
	ReportService reportService;
	
	
	@Autowired
	ReportWriter er;
	
	
	@GetMapping("/create-jobs")
	public void test() {
		log.info("Creating jos reuest recieved.");
		jobCreator.triggerJobCreator();
		//reportGenerator.triggerProjectReportSchedulerJob();
	}
	
	@PostMapping("/save")
	public Report saveReport(@RequestBody Report report) {
		report.setReportId(ReportUtil.generate_report_Id());
		report.setCreatedDate(ReportUtil.getTimeStamp());
		report.setStatus("DRAFT");
		return reportService.saveReport(report);
	}
	
	@GetMapping("/list")
	public List<Report> getReportsList() {
		return reportService.getReportList();
	}
	
	@GetMapping("/details/{reportId}")
	public Object getReportDetails(@PathVariable("reportId") String reportId) {
	return reportService.getReportDetails(reportId);
	}
	
	/*
	@GetMapping("/list")
	public String getReportsList() {
		List<ReportGenerator.ReportData> list = reportGenerator.getReportList();
		return list.toString();
	}
	@GetMapping("/list1")
	public List<ReportGenerator.ReportData> getReportsList1() {
		List<ReportGenerator.ReportData> list = reportGenerator.getReportList();
		return list;
	}
	*/
	
	@GetMapping("/downloadpdf/{component}/{reportId}")
	public void gettaskReport(HttpServletResponse reponse,@PathVariable("component") String component,@PathVariable("reportId") String reportId) throws ReportGenerateException {
		er.taskReportWriter(reponse,reportId,component);
	}
	
	@GetMapping("/log/{reportId}")
	public List<ReportHistory> getReportHistory(@PathVariable("reportId") String reportId){
		return null;
	}
	
}
