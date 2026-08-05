package com.sabtok.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sabtok.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sabtok.dao.MyQuestionDao;
import com.sabtok.entity.MyQuestion;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class MyQuestionController {

	@Autowired
	private MyQuestionDao questionDao;
	
	@PostMapping("/save")
	public MyQuestion saveQuestion(@RequestBody MyQuestion question) {
		question.setStatus("OPEN");
		return questionDao.save(question);
	}
	
	@GetMapping("/list")
	public List<MyQuestion> getQuestionList(){
		return questionDao.findAll();
	}
	
	@GetMapping("/details/{id}")
	public MyQuestion getQuestionDetails(@PathVariable("id") Long questionId){
		return questionDao.findById(questionId).get();
	}

	@GetMapping("/details")
	public List<MyQuestion> getQuestionDetailsForIdList(@RequestParam("questionNos") String questionNos){
		List<Long> qIds = Arrays.stream(questionNos.split(","))
				.mapToLong(Long::valueOf)
				.boxed()
				.collect(Collectors.toList());
		return questionDao.findAllById(qIds);
	}

	@GetMapping("/status/{questionNo}")
	public Boolean closeQuestion(@PathVariable("questionNo") long questionNo) {
		MyQuestion question = questionDao.findById(questionNo).get();
		question.setUpdatedBy("MyQuestionController - CLOSED");
		question.setUpdatedAt(LocalDateTime.now());
		questionDao.save(question);
		 questionDao.closeQuestion(questionNo);
		return true;
	}
	
}
