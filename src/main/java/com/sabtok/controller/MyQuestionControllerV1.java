package com.sabtok.controller;

import com.sabtok.dao.MyQuestionDao;
import com.sabtok.entity.Comment;
import com.sabtok.entity.MyQuestion;
import com.sabtok.services.MyQuestionServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MyQuestionControllerV1 {

    private final MyQuestionServiceI myQuestionService;

    private  final MyQuestionDao myQuestionDao;

    @GetMapping("/questions")
    public Page<MyQuestion> getQuestions(@RequestParam(value = "pageNo", defaultValue = "1")  int pageNo,
                                                        @RequestParam(value = "pageLimit", defaultValue = "10") int pageLimit) {
        Pageable pageable = PageRequest.of(pageNo - 1,pageLimit);
        return myQuestionDao.findAll(pageable);
    }
}
