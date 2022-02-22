package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Report;

public interface ReportDao  extends JpaRepository<Report, Integer>{

	public Report findByReportId(String reportId);
}
