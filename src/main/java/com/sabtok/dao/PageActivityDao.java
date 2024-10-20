package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.PageActivity;

import java.util.List;

@Repository
public interface PageActivityDao extends JpaRepository<PageActivity, String>{

    List<PageActivity> findAllByPageId(String pageId);
}
