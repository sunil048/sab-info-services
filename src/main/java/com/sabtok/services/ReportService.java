package com.sabtok.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sabtok.persistance.entity.Report;

@Service
public interface ReportService {

	public Report saveReport(Report report);
	public List<Report> getReportList();
	public Report getReportDetails(String reportId);
	public void updateStatus(String reportId);
}
