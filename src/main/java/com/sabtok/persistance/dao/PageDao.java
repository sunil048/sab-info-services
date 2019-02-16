package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Page;

public interface PageDao extends JpaRepository<Page, Long>{

}
