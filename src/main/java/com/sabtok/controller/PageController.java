package com.sabtok.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.entity.Page;
import com.sabtok.entity.PageActivity;
import com.sabtok.entity.PageEventAction;
import com.sabtok.exception.PageException;
import com.sabtok.services.PageService;
import com.sabtok.services.impl.PageActivityServiceImpl;
import com.sabtok.util.SabInfoUtil;
import com.sabtok.util.StringDateConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/page")
@CrossOrigin("*")
@Slf4j
public class PageController {
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	PageActivityServiceImpl pageActivityServiceImpl;
	
	@Autowired
	SabInfoUtil util;
	
	@GetMapping("/all")
	public List<Page> getAllPagesList(){
		return pageService.getAllPagesList();
	}
	
	@GetMapping("/total")
	public Long getPageCount() {
		return pageService.getPageCount();
	}
	
	@GetMapping("/pagecount/{bookId}")
	public Integer getPageCountForBook(@PathVariable("bookId") String bookId) {
		return pageService.getPageListByBookId(bookId).size();
	}
	
	@GetMapping("/details/{pageId}")
	public Page getPageDetails(@PathVariable("pageId") String pageId) {
		return pageService.getPageDetails(pageId);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity creatPage(@RequestBody Page page) {
		log.info("creatPage() called.....");
		try {
			page.setCreatedDate(StringDateConverter.getTimeStamp());
			int pageNo = pageService.creatPage(page);
			return new ResponseEntity<Integer>(pageNo,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error("ERROR while creating page...", e);
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity updatePage(@RequestBody Page page) {
		log.info("method updatePage() is called...");
		try {
			if (StringUtils.isEmpty(page.getContent())) {
				throw new PageException("Page content is empty");
			}
			String pageId = pageService.updatePage(page);
			return new ResponseEntity<String>(pageId,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error("ERROR while updating page "+page.getPageId(),e);
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/no/{pageNo}")
	public ResponseEntity<Page> getPageDetailsByBookNo(@PathVariable("pageNo") String pageNo){
		log.info("getPageDetailsByBookId "+pageNo);
		System.out.println("hello");
		Page page= pageService.getPageDetailsByBookNo(Long.valueOf(pageNo));
		return new ResponseEntity<Page>(page, HttpStatus.OK);
		
	}
	
	@GetMapping("/pageList/{bookId}")
	public  List<Page> getPageListByBookId(@PathVariable("bookId") String bookId){
		log.info("Getting page List for given book id "+bookId);
		return  pageService.getPageListByBookId(bookId);
		
	}
	
}
