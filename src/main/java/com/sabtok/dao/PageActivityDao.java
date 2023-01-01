package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.PageActivity;

@Repository
public interface PageActivityDao extends JpaRepository<PageActivity, String>{

}
