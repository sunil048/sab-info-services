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
import com.sabtok.entity.Comment;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentsController {

	@Autowired
	private CommentDao commentDao;
	
	@PostMapping("/save")
	public Comment saveComment(@RequestBody Comment comment) {
		System.out.println(comment);
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
