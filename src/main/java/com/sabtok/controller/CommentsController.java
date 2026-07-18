package com.sabtok.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.sabtok.dao.MyQuestionDao;
import com.sabtok.entity.MyQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.dao.CommentDao;
import com.sabtok.entity.Comment;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentsController {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private MyQuestionDao questionDao;
	
	@PostMapping("/save")
	public Comment saveComment(@RequestBody Comment comment) {
		System.out.println(comment);
		MyQuestion question = questionDao.findById(comment.getQuestionId()).get();
		question.setUpdatedBy("Comment Controller");
		question.setUpdatedAt(LocalDateTime.now());
		questionDao.save(question);
		return commentDao.save(comment);
	}
	
	@GetMapping("/list")
	public List<Comment> getCommentList(){
		return commentDao.findAll();
	}
	
	@GetMapping("/details/{id}")
	public Comment getQuestionDetails(@PathVariable("id") Long commentId){
		return commentDao.findById(commentId).get();
	}
}
