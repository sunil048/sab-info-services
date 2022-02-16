package com.sabtok.report.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.report.service.JobService;

@RestController
@CrossOrigin("*")
@RequestMapping("job")
public class JobController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobService jobService;
	
	@Autowired
	com.sabtok.JobRunner jobRunner;

	@GetMapping("/details/{reportId}")
	public Object getJobDetails(@PathVariable("reportId") String reportId) {
		logger.info("Recived the request to get job details.");
		return jobService.getJobList(reportId);
	}
	
	@GetMapping("/trigger")
	public void triggerJobs() {
		logger.info("Recived the request for job trigger.");
		jobRunner.processProjectJobs();
		jobRunner.processTaskJobs();
	}
}
