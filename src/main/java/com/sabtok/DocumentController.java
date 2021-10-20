package com.sabtok;

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
import javax.validation.Valid;

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

import com.sabtok.persistance.dao.DocumentDao;
import com.sabtok.persistance.entity.Attachement;
import com.sabtok.util.JsonUtil;
import com.sabtok.util.StringDateConverter;


@RestController
@RequestMapping("/doc")
@CrossOrigin("http://localhost:4000")
public class DocumentController {
	
	@Autowired
	DocumentDao documentDao;
	
	@GetMapping("/list")
	public List <Attachement> getDocumentList(){
		return documentDao.findAll();
	}
	
	@GetMapping("/list/{pageid}")
	public List <Attachement> getDocumentListForPage(@PathVariable("pageid") String pageId){
		return documentDao.getDocumentListByPageIdOrderByAttachementNoAsc(pageId);
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

	 @PostMapping("/save")
	 public String saveDocument(@RequestParam("BODY") String doctPayload,@RequestParam(value="DOCUMENT",required=false) MultipartFile attachedFile) throws IOException, SerialException, SQLException {
		 Attachement doc = (Attachement) JsonUtil.converStringToObject(doctPayload, Attachement.class);
		 byte [] bytedata = attachedFile.getBytes();
		 Blob myBlob = new SerialBlob(bytedata);
		 doc.setContent(myBlob);
		 doc.setCreatedDate(StringDateConverter.getTimeStamp());
		 doc.setFileName(attachedFile.getOriginalFilename());
		 doc.setSize(attachedFile.getSize()/1024);
		 doc.setFileType(attachedFile.getContentType());
		 documentDao.save(doc);
		 return doc.getCreatedDate();
	 }
	 
	 @GetMapping("/doc-next-no/{pageId}")
	 public Integer getPageNumber(@PathVariable("pageId") String pageId) {
		 return documentDao.getTotalPageCount(pageId)+1;
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
