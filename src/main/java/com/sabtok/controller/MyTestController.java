package com.sabtok.controller;

import com.sabtok.dao.MyQuestionDao;
import com.sabtok.dao.MyTestDao;
import com.sabtok.entity.MyQuestion;
import com.sabtok.entity.MyTest;
import com.sabtok.entity.TestQuestion;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class MyTestController {

    @Autowired
    private MyTestDao myTestDao;

    @Autowired
    private MyQuestionDao myQuestionDao;

    @PostMapping("/generate")
    public MyTest generateTest(@RequestBody MyTest myTest) {


        Specification<MyQuestion> spec = MyQuestionSpecifications.matchTestCriteria(myTest);
        List<MyQuestion> questions = myQuestionDao.findAll(spec);
        Collections.shuffle(questions);
        List<TestQuestion> selectedQuestions = questions.stream()
                .limit(5) // Safely stops at 5 or the list size automatically
                .map(qu -> TestQuestion.builder()
                        .id(UUID.randomUUID().toString())
                        .questionId(qu.getQuestionId())
                        .questionName(qu.getQuestion())
                        .isAnswered(false)
                        .myTest(myTest)
                        .build())
                .collect(Collectors.toList());

        myTest.setQuestions(selectedQuestions);
        return myTestDao.save(myTest);
    }

    @GetMapping("/data")
    public MyTest getMyTest(){

        MyTest myTest = MyTest.builder()
                .name("test")
                .category("mock")
                .date(LocalDateTime.now())
                .build();
        List<MyQuestion> questions = myQuestionDao.findAllByCategory("Interview");
        Collections.shuffle(questions);
        List<TestQuestion> selectedQuestions = questions.stream()
                .limit(5) // Safely stops at 5 or the list size automatically
                .map(qu -> TestQuestion.builder()
                        .id(UUID.randomUUID().toString())
                        .questionId(qu.getQuestionId())
                        .questionName(qu.getQuestion())
                        .isAnswered(false)
                        .myTest(myTest)
                        .build())
                .collect(Collectors.toList());

        myTest.setQuestions(selectedQuestions);
        myTestDao.save(myTest);
        return myTest;
    }

    @PostMapping("/save")
    public MyTest saveTestResult(@RequestBody MyTest myTest) {

        List<TestQuestion> testQuestions = myTest.getQuestions();
        testQuestions.forEach(testQuestion -> {testQuestion.setTestId(myTest.getId());
        testQuestion.setMyTest(myTest);});
        return myTestDao.save(myTest);

    }

    @GetMapping("/")
    public List<MyTest> getAllTest(){
        return myTestDao.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MyTest> getAllTest(@PathVariable("id") Long id){
        return myTestDao.findById(id);
    }

}


