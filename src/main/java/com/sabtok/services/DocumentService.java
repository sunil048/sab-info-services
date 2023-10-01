package com.sabtok.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.web.multipart.MultipartFile;

import com.sabtok.entity.Attachement;

public interface DocumentService {

	public List <Attachement> getDocumentList();
	public List <Attachement> getDocumentListForPage(String pageId);
	public Attachement uploadFile(Attachement attachement);
	public Attachement getDocumentByAttachementId(String attachementId);
	public Integer getPageNumber(String pageId);
	public String saveDocument(String doctPayload,MultipartFile attachedFile) throws SerialException, SQLException, IOException;
	public Long getTotalDocumentCount();
}
