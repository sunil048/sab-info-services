package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.MyQuestion;

@Repository
public interface MyQuestionDao extends JpaRepository<MyQuestion, Long> {

}
