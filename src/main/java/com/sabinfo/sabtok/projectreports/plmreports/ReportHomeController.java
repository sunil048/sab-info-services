package com.sabinfo.sabtok.projectreports.plmreports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportHomeController {

	@RequestMapping("/plmreport")
	public String plmHomePage(){
		
		return "plmreportpage";
	}
}
