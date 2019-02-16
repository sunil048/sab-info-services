package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Attachement;

public interface DocumentDao extends JpaRepository<Attachement, Long> {

}
