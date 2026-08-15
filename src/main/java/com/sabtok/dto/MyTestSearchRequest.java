package com.sabtok.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MyTestSearchRequest {

    private String name;               // Match partial text
    private List<String> categories;   // Match multiple values (IN clause)
    private List<String> subCategories;   // Match multiple values (IN clause)
    private List<String> skills;
    private List<String> subSkills;
    private List<String> priorities;   // Match multiple values (IN clause)
    private List<String> levels;       // Match multiple values (IN clause)
    private Integer noOfQuestions;
    private LocalDateTime date;

}
