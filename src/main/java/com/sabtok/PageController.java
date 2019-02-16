package com.sabtok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.PageDao;
import com.sabtok.persistance.entity.Log;
import com.sabtok.persistance.entity.LogAction;
import com.sabtok.persistance.entity.Page;
import com.sabtok.util.SabInfoUtil;

@RestController
@RequestMapping("/page")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao","com.sabtok.util.*"})
public class PageController {

	@Autowired
	PageDao pageRepo;
	
	@Autowired
	SabInfoUtil util;
	
	@GetMapping("/all")
	public List<Page> getAllPagesList(){
		return pageRepo.findAll();
	}
	
	@GetMapping("/total")
	public Long getPageCount() {
		return pageRepo.count();
	}
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Long creatPage(@RequestBody Page page) {
		Log log = new Log();
		log.setPageId(page.getPageId());
		log.setAction(LogAction.CREATED);
		util.updateLogFields(log);
		pageRepo.save(page);
		return page.getPageNo();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updatePage(@RequestBody Page page) {
		Log log = new Log();
		log.setPageId(page.getPageId());
		log.setAction(LogAction.MODIFIED);
		util.updateLogFields(log);
		pageRepo.save(page);
		return LogAction.MODIFIED+" Page "+page.getPageId();
	}
	
}
