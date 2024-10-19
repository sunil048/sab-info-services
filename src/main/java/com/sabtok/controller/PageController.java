package com.sabtok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.sabtok.UserStoryClient;
import com.sabtok.entity.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.sabtok.exception.PageException;
import com.sabtok.services.PageService;
import com.sabtok.services.impl.PageActivityServiceImpl;
import com.sabtok.util.SabInfoUtil;
import com.sabtok.util.StringDateConverter;

import lombok.extern.slf4j.Slf4j;

import static com.sabtok.ApiCons.PAGE;
import static com.sabtok.ApiCons.SAVE;

@RestController
@RequestMapping(PAGE)
@CrossOrigin("*")
@Slf4j
public class PageController {
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	PageActivityServiceImpl pageActivityServiceImpl;
	
	@Autowired
	SabInfoUtil util;
	
	private Map<String, String> recentViewdPages = new HashMap();
	
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
		Page page = pageService.getPageDetails(pageId);
		recentViewdPages.put(page.getPageId(), page.getTitle());
		return page;
	}


	@Operation(summary="Save the page details")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Successfully retrived book number count"),
					@ApiResponse(responseCode = "400", description = "Request not valid"),
					@ApiResponse(responseCode = "422", description = "Request not valid"),
					@ApiResponse(responseCode = "500", description = "Error processing request")
			}
	)
	@RequestMapping(value=SAVE,method=RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity creatPage(@RequestBody Page page) {
		log.info("creatPage() called.....");
		try {
			page.setCreatedDate(StringDateConverter.getTimeStamp());
			int pageNo = pageService.creatPage(page);
			return new ResponseEntity<Integer>(pageNo,HttpStatus.OK);
		} catch (Exception e) {
			log.error("ERROR while creating page...", e);
			return new ResponseEntity<Object>(e,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/update/{backUpFlag}",method=RequestMethod.POST)
	public ResponseEntity updatePage(@PathVariable("backUpFlag") String backUpFlag,@RequestBody Page page) {
		log.info("method updatePage() is called...");
		try {
			if (StringUtils.isEmpty(page.getContent())) {
				throw new PageException("Page content is empty");
			}
			String pageId = pageService.updatePage(page, Boolean.valueOf(backUpFlag));
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
	
	@GetMapping("/viewed-pages")
	public ResponseEntity<Object> getRecentViewedPages(){
		return new ResponseEntity<>(recentViewdPages,HttpStatus.OK);
	}
	
	@PutMapping("/viewed-pages/clear")
	public ResponseEntity<Object> clearRecentViewedPages(){
		recentViewdPages.clear();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/link/types")
	public LinkageType[] getPageLinkageType(){
		return LinkageType.values();
	}

	@PutMapping("/link/save/{pageId}/{item}/{linkType}")
	public Object linkItemToPage(@PathVariable("pageId") String pageId,
								 @PathVariable("item") String item,
								 @PathVariable("linkType") LinkageType linkType){
		return pageService.linkPage(pageId,item,linkType);
	}

	@GetMapping(value="/links/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PageLinkage> getPageLinkageType(@PathVariable("pageId") String pageId){
		return pageService.getPageLinkedItems(pageId);
	}


	@GetMapping(value="/activities/{pageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getActivitiesForPage(@PathVariable("pageId") String pageId) {
		Objects.requireNonNull(pageId);
		return pageActivityServiceImpl.getActivitiesForPage(pageId);
	}

	@Autowired
	private UserStoryClient userStoryClient;

	@GetMapping("/test/{id}")
	public Object test(@PathVariable("id") String id){
		return userStoryClient.getUserStoryDetails(id);
	}
	
}
