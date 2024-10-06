package com.sabtok.dao;

import com.sabtok.entity.PageLinkage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageLinkageDao extends JpaRepository<PageLinkage, String> {

    public List<PageLinkage> findAllByPageId(String pageId);
}
