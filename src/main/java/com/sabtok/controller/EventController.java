package com.sabtok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sabtok.dao.EventDao;

@RestController
@RequestMapping("/event")
public class EventController {

}
/*
	
	EventDao logDao;
	@GetMapping("/all")
	public List<Event> getBooks(){
		return logDao.findAll();
	}
	
	@RequestMapping(value="/load",method=RequestMethod.POST)
	@ResponseBody
	public String saveBook(@RequestBody Event log) {
		System.out.println("calling method and saving entity");
		System.out.println(log);
		logDao.save(log);
		return "success";
	}

*/