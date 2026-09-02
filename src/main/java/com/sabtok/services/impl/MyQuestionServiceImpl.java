package com.sabtok.services.impl;

import com.sabtok.dao.MyQuestionDao;
import com.sabtok.entity.MyQuestion;
import com.sabtok.services.MyQuestionServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyQuestionServiceImpl implements MyQuestionServiceI {

    private final MyQuestionDao myQuestionDao;

    @Override
    public Page<MyQuestion> getQuestions(Pageable pageable) {
        return myQuestionDao.findAll(pageable);
    }

}
