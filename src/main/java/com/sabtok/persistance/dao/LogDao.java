package com.sabtok.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabtok.persistance.entity.Log;

public interface LogDao extends JpaRepository<Log, Long>{

}
