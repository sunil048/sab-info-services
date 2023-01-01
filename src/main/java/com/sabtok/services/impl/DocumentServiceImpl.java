package com.sabtok.services.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sabtok.dao.DocumentDao;
import com.sabtok.entity.Attachement;
import com.sabtok.entity.PageEventAction;
import com.sabtok.services.DocumentService;
import com.sabtok.util.JsonUtil;
import com.sabtok.util.StringDateConverter;


@Component
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private PageActivityServiceImpl pageActivityService;
	
	@Override
	public List<Attachement> getDocumentList() {
		return documentDao.findAll();
	}

	@Override
	public List<Attachement> getDocumentListForPage(String pageId) {
		return documentDao.getDocumentListByPageIdOrderByAttachementNoAsc(pageId);
	}

	@Override
	public Attachement uploadFile(Attachement attachement) {
		System.out.println("uploading file");
		return documentDao.save(attachement);
	}

	@Override
	public Attachement getDocumentByAttachementId(String attachementId) {
		return documentDao.getDocumentByAttachementId(attachementId);
	}

	@Override
	public Integer getPageNumber(String pageId) {
		 return documentDao.getTotalPageCount(pageId)+1;
	}

	@Override
	public String saveDocument(String doctPayload, MultipartFile attachedFile) throws SerialException, SQLException, IOException 
	{
		try {
			 Attachement doc = (Attachement) JsonUtil.converStringToObject(doctPayload, Attachement.class);
			 byte [] bytedata = attachedFile.getBytes();
			 Blob myBlob = new SerialBlob(bytedata);
			 doc.setContent(myBlob);
			 doc.setCreatedDate(StringDateConverter.getTimeStamp());
			 doc.setFileName(attachedFile.getOriginalFilename());
			 doc.setSize(attachedFile.getSize()/1024);
			 doc.setFileType(attachedFile.getContentType());
			 Attachement docObj = documentDao.save(doc);
			 if (docObj != null) {
				 pageActivityService.logPageActivity(docObj, PageEventAction.DOCUPLOAD);
			 }
			 return doc.getCreatedDate();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
