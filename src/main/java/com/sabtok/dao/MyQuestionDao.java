package com.sabtok.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sabtok.entity.MyQuestion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MyQuestionDao extends JpaRepository<MyQuestion, Long>, JpaSpecificationExecutor<MyQuestion> {

    @Transactional
    @Modifying
    @Query(value = "update QUESTIONERIES set status='CLOSED' where id=:questionNo", nativeQuery = true)
    public int closeQuestion(@Param("questionNo") long questionNo);

    public List<MyQuestion> findAllByCategory(String category);

    // Triggers an eager fetch of the 'subSelectRelation' collection for this query only
    //@EntityGraph(attributePaths = {"comments"},type = EntityGraph.EntityGraphType.FETCH)
    //@Query("select q from MyQuestion q left join fetch q.comments")
    Page<MyQuestion> findAll(Pageable pageable);
}
