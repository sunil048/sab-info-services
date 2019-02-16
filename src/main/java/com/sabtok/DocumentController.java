package com.sabtok;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sabtok.persistance.dao.DocumentDao;
import com.sabtok.persistance.entity.Attachement;

@RestController
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	DocumentDao documentDao;
	
	@PostMapping("/upload")
	public Attachement uploadFile(@Valid @RequestBody Attachement attachement) {
		System.out.println("uploading file");
		return documentDao.save(attachement);
	}
	
	@GetMapping("/test")
	public String test() {
		return "success";
	}

	/*@PostMapping("/upload1")
	public String uploadFile1(@RequestParam("file") MultipartFile file) {
		Attachement doc = new Attachement();
		doc.setAttachementId(Math.random()+"");
		doc.setAttachementNo(5);
		doc.setCreatedBy("Test");
		doc.setPageId("Test");
		doc.setTitle("test title");
		doc.setContent(content);
		System.out.println("uploading file");
		return "success";
	}*/
}
