package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.MyQuestion;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MyQuestionDao extends JpaRepository<MyQuestion, Long> {

    @Transactional
    @Modifying
    @Query(value = "update QUESTIONERIES set status='CLOSED' where id=:questionNo", nativeQuery = true)
    public int closeQuestion(@Param("questionNo") long questionNo);
}
