package com.sabtok.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TEST_QUESTIONS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestQuestion {

    @Id
    private String id;

    @Column(name = "TEST_ID", updatable = false,insertable = false,nullable = false)
    private Long testId;

    @Column(name="QUESTION_ID")
    private long questionId;

    @Column(name="QUESTION_NAME")
    private String questionName;

    @Column(name="IS_ANSWERED")
    private Boolean isAnswered;

    @Column(name="COMMENT")
    private String comment;

    @JoinColumn(name = "TEST_ID")
    @ManyToOne
    @JsonIgnore
    private MyTest myTest;


}
