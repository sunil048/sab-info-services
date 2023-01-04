package com.sabtok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.Event;


@Repository
public interface EventDao extends JpaRepository<Event, Long>{

}
