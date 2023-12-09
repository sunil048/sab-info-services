package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

}
