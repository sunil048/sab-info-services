package com.sabtok.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="MY_TESTS")
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyTest {

    @Id
    @Column(name="ID")
    @GeneratedValue(generator="test_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="test_seq",sequenceName="test_sequence", initialValue = 1, allocationSize=1)
    private Long Id;

    @Column(name="NAME")
    private String name;

    @Column(name="CATEGORY")
    private String category;

    @Column(name = "SUB_CATEGORY")
    private String subCategory;

    @Column(name="DATE")
    private LocalDateTime date;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "SUB_SKILL")
    private String subSkill;

    @Column(name = "NO_OF_QUESTIONS")
    private Integer noOfQuestions;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "Level")
    private String level;

    @Column(name = "RESULT")
    private String result;

    @Column(name = "MARKS")
    private String marks;

    @Column(name = "COMMENTS")
    private String comments;

    @OneToMany(mappedBy = "myTest",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestQuestion> questions;

    @PrePersist
    public void prePersist(){
        if (questions != null) {
            questions.forEach(q -> q.setMyTest(this));
        }
    }


}
