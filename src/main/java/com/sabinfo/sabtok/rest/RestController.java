package com.sabinfo.sabtok.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value="/rest/*")
public class RestController {
	
	@RequestMapping(value="/Page",method=RequestMethod.GET)
	public String test(){
		return "success";
	}

}
