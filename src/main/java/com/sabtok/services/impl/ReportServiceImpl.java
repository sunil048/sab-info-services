package com.sabtok.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sabtok.persistance.dao.ReportDao;
import com.sabtok.persistance.entity.Report;
import com.sabtok.services.ReportService;


@Component
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao rDao;
	
	@Override
	public Report saveReport(Report report) {
		report.setSlNO(String.valueOf(rDao.count()+1));
		return rDao.save(report);
	}

	@Override
	public List<Report> getReportList() {
		return rDao.findAll();
	}

	@Override
	public Report getReportDetails(String reportId) {
		return rDao.findByReportId(reportId);
	}

	@Override
	public void updateStatus(String reportId) {
		// TODO Auto-generated method stub
		
	}

}
