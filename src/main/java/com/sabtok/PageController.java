package com.sabtok;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.PageDao;
import com.sabtok.persistance.entity.Book;
import com.sabtok.persistance.entity.Event;
import com.sabtok.persistance.entity.EventAction;
import com.sabtok.persistance.entity.Page;
import com.sabtok.util.SabInfoUtil;

@RestController
@RequestMapping("/page")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao","com.sabtok.util.*"})
@CrossOrigin(origins = "http://localhost:4200")
public class PageController {

	Logger log = Logger.getLogger(PageController.class);
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
		Event log = new Event();
		log.setPageId(page.getPageId());
		log.setAction(EventAction.CREATED);
		util.updateLogFields(log);
		pageRepo.save(page);
		return page.getPageNo();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String updatePage(@RequestBody Page page) {
		Event log = new Event();
		log.setPageId(page.getPageId());
		log.setAction(EventAction.MODIFIED);
		util.updateLogFields(log);
		pageRepo.save(page);
		return EventAction.MODIFIED+" Page "+page.getPageId();
	}
	
	@GetMapping("/no/{pageNo}")
	public ResponseEntity<Page> getPageDetailsByBookNo(@PathVariable("pageNo") String pageNo){
		log.info("getPageDetailsByBookId "+pageNo);
		System.out.println("hello");
		Page page= pageRepo.findOne(Long.valueOf(pageNo));
		return new ResponseEntity<Page>(page, HttpStatus.OK);
		
	}
	
	@GetMapping("/pageList/{bookId}")
	public  List<Page> getPageDetailsByBookId(@PathVariable("bookId") String bookId){
		log.info("getBookDetailsByBookId "+bookId);
		System.out.println("hello");
		 List<Page> pageList = new ArrayList<Page>();
		for (Page page : getAllPagesList())
			if (page.getBookId().equals(bookId))
				pageList.add(page);
		return pageList;
		
	}
	
}
