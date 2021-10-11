package com.sabtok.persistance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Attachement;

public interface DocumentDao extends JpaRepository<Attachement, Long> {
	
	public List<Attachement> getDocumentListByPageId(String pageId);
	public Attachement getDocumentByAttachementId(String attachementId);

}
