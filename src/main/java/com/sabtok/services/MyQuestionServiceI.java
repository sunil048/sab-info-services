package com.sabtok.services;

import com.sabtok.entity.MyQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyQuestionServiceI {
    Page<MyQuestion> getQuestions(Pageable pageable);
}
