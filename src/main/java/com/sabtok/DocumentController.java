package com.sabtok;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin("*")
public class DocumentController {
	
	@Autowired
	DocumentDao documentDao;
	
	@GetMapping("/list")
	public List <Attachement> getDocumentList(){
		return documentDao.findAll();
	}
	
	@GetMapping("/list/{pageid}")
	public List <Attachement> getDocumentListForPage(@PathVariable("pageid") String pageId){
		return documentDao.getDocumentListByPageId(pageId);
	}
	
	@PostMapping("/upload")
	public Attachement uploadFile(@Valid @RequestBody Attachement attachement) {
		System.out.println("uploading file");
		return documentDao.save(attachement);
	}
	
	@GetMapping("/test")
	public String test() {
		return "success";
	}

	
	 @GetMapping("/downloadAttachment/{attachementId}")
	    public String downloadBookPhotograph(@PathVariable("attachementId") String attachementId, HttpServletResponse response) {
	        Attachement doc  = documentDao.getDocumentByAttachementId(attachementId);
	        try {
	            if (doc.getContent()!=null) {
	                response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getTitle()+ "\"");
	                OutputStream out = response.getOutputStream();
	               /* response.setContentType("image/gif");
	                response.setContentType("image/jpg");*/
	               /* response.setContentType("text/html");*/
	                IOUtils.copy(doc.getContent().getBinaryStream(), out);
	                out.flush();
	                out.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return null;
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
