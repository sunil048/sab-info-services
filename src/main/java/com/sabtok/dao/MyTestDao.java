package com.sabtok.dao;

import com.sabtok.entity.MyTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTestDao extends JpaRepository<MyTest,Long> {
}
