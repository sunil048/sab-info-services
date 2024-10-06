package com.sabtok.dao;

import com.sabtok.entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryDao extends JpaRepository<UserStory, String> {
}
