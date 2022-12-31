package com.sabtok.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
//import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sabtok.entity.Attachement;
import com.sabtok.services.DocumentService;
import com.sabtok.util.JsonUtil;
import com.sabtok.util.StringDateConverter;


@RestController
@RequestMapping("/doc")
@CrossOrigin("*")
public class DocumentController {
	
	@Autowired
	DocumentService documentService;
	
	@GetMapping("/list")
	public List <Attachement> getDocumentList(){
		return documentService.getDocumentList();
	}
	
	@GetMapping("/list/{pageid}")
	public List <Attachement> getDocumentListForPage(@PathVariable("pageid") String pageId){
		return documentService.getDocumentListForPage(pageId);
	}
	
	@PostMapping("/upload")
	public Attachement uploadFile(@RequestBody Attachement attachement) {
		System.out.println("uploading file");
		return documentService.uploadFile(attachement);
	}
	
	 @GetMapping("/downloadAttachment/{attachementId}")
	 public String downloadBookPhotograph(@PathVariable("attachementId") String attachementId, HttpServletResponse response) {
	        Attachement doc  = documentService.getDocumentByAttachementId(attachementId);
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

	 @PostMapping("/save")
	 public String saveDocument(@RequestParam("BODY") String doctPayload,@RequestParam(value="DOCUMENT",required=false) MultipartFile attachedFile) throws IOException, SerialException, SQLException {
		return documentService.saveDocument(doctPayload, attachedFile);
	 }
	 
	 @GetMapping("/doc-next-no/{pageId}")
	 public Integer getPageNumber(@PathVariable("pageId") String pageId) {
		 return documentService.getPageNumber(pageId)+1;
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
