package com.sabtok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabtok.persistance.dao.PageDao;
import com.sabtok.persistance.entity.Page;

@RestController
@RequestMapping("/page")
@EnableJpaRepositories
@ComponentScan(basePackages= {"com.sabtok.persistance.dao"})
public class PageController {

	@Autowired
	PageDao pageRepo;
	
	@GetMapping("/all")
	public List<Page> getAllPagesList(){
		return pageRepo.findAll();
	}
	
	@GetMapping("/total")
	public Long getPageCount() {
		return pageRepo.count();
	}
	
}
