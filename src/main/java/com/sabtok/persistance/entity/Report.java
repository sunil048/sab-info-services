package com.sabtok.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REPORTS")
public class Report {

	@Id
	String slNO;
	String reportId;
	String reportname;
	String createdDate;
	String component;
	String sub_component;
	String status;
	String destinationFolder;
	String inputData;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSlNO() {
		return slNO;
	}
	public void setSlNO(String slNO) {
		this.slNO = slNO;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportname() {
		return reportname;
	}
	public void setReportname(String reportname) {
		this.reportname = reportname;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getSub_component() {
		return sub_component;
	}
	public void setSub_component(String sub_component) {
		this.sub_component = sub_component;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDestinationFolder() {
		return destinationFolder;
	}
	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	
	
}
