package com.sabtok.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.exception.SabInfoException;
import com.sabtok.services.BookService;
import com.sabtok.services.DocumentService;
import com.sabtok.services.PageService;
import com.sabtok.UserStoriesScheduler;

/**
 * @author user
 * Sep 20, 2023 8:38:02 AM
 */
@RestController
@RequestMapping("/board")
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private DocumentService documentService;

	@Autowired
	private UserStoriesScheduler userStoriesScheduler;
	
	@GetMapping("/counts")
	public ResponseEntity<Object> getDashBoradDetails()
	{
		try 
		{
			Map <String, String> countsDetails = new HashMap<>();
			countsDetails.put("TOTAL_BOOK_COUNT", bookService.bookNumber().toString());
			countsDetails.put("TOTAL_PAGE_COUNT", pageService.getPageCount().toString());
			countsDetails.put("TOTAL_DOCUMENT_COUNT", documentService.getTotalDocumentCount().toString());
			return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(countsDetails);
		} catch (Exception e) {
			SabInfoException error = new SabInfoException("500",e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).internalServerError().body(error);
		}
	}

	@GetMapping("/test")
	public Object getData(){
		return userStoriesScheduler.getUserStoryData();
	}
}
