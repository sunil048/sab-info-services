package com.sabtok.persistance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sabtok.persistance.entity.Attachement;

@Repository
public interface DocumentDao extends JpaRepository<Attachement, Long> {
	
	public List<Attachement> getDocumentListByPageId(String pageId);
	public List<Attachement> getDocumentListByPageIdOrderByAttachementNoAsc(String pageId);
	public Attachement getDocumentByAttachementId(String attachementId);
	@Query("select count(*) from Attachement where pageId=:pageId")
	public Integer getTotalPageCount(@Param("pageId") String pageId);

}
