package com.sabtok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.dao.CommentDao;
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
	
}
