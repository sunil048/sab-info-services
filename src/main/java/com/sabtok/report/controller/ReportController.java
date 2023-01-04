package com.sabtok.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.sabtok.entity.Page;
import com.sabtok.report.service.ReportService;
import com.sabtok.report.template.GeneratePdfReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/report/page/{pageId}")
	public ResponseEntity<InputStreamResource> printPageAsPdf(@PathVariable("pageId") String pageId)
			throws IOException {
		log.info("Getting page details "+pageId);
		Page page = reportService.getPageDetails(pageId);

		ByteArrayInputStream bis = GeneratePdfReport.generatePDFForPage(page);//

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=page-"+pageId+".pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	
	
	public ModelAndView generatePDFReportForPage1(@PathVariable("pageId") String pageId){
		Map<String,Page> map = new HashMap <String,Page>();
		try{
			log.info("Getting page details "+pageId);
			Page page = reportService.getPageDetails(pageId);
			map.put("Page",page);
			ModelAndView view  = new ModelAndView("PagePDFTemplate", "Page", map);
			log.info("Calling page pdf file generate template");
			return view;
		}catch (Exception e) {
			log.error("Page does not exist "+pageId);
			return new ModelAndView("errorpage", "exception", e);
		}
		
	}
	
}
