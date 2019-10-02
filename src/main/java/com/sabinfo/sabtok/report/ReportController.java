package com.sabinfo.sabtok.report;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sabinfo.sabtok.wiki.controller.HomeController1;
import com.sabinfo.sabtok.wiki.dao.BookDaoImpl;
import com.sabinfo.sabtok.wiki.model.Page;

@Controller
public class ReportController {

	private static Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired
	BookDaoImpl bookDAOIpml;
	
	
	@RequestMapping(value="/report/page/{pageId}")
	public ModelAndView generatePDFReportForPage(@PathVariable("pageId") String pageId){
		Map<String,Page> map = new HashMap <String,Page>();
		try{
			logger.info("Getting page details "+pageId);
			Page page = bookDAOIpml.getPageDetails(pageId);
			map.put("Page",page);
			ModelAndView view  = new ModelAndView("PagePDFTemplate", "Page", map);
			logger.info("Calling page pdf file generate template");
			return view;
		}catch (Exception e) {
			logger.error("Page does not exist "+pageId);
			return new ModelAndView("errorpage", "exception", e);
		}
		
	}

}
