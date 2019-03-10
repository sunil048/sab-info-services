package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Event;

public interface EventDao extends JpaRepository<Event, Long>{

}
