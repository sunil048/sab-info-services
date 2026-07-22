package com.sabtok.controller;

import com.sabtok.entity.MyQuestion;
import com.sabtok.entity.MyTest;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MyQuestionSpecifications {

    public static Specification<MyQuestion> matchTestCriteria(MyTest criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria == null) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }

            // 1. Partial Text Match (Case-Insensitive) for Category mapping
            if (criteria.getCategory() != null && !StringUtils.isBlank(criteria.getCategory())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("category")),
                        "%" + criteria.getCategory().toLowerCase() + "%"
                ));
            }

           /* // 2. Partial Text Match for Sub-Category mapping
            if (criteria.getSubCategory() != null && ! StringUtils.isBlank(criteria.getSubCategory())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("subCategory")),
                        "%" + criteria.getSubCategory().toLowerCase() + "%"
                ));
            }

            // 3. Exact Match for Skill area
            if (criteria.getSkill() != null && !StringUtils.isBlank(criteria.getSkill())) {
                predicates.add(criteriaBuilder.equal(root.get("skill"), criteria.getSkill()));
            }

            // 4. Exact Match for Sub-Skill area
            if (criteria.getSubSkill() != null && !StringUtils.isBlank(criteria.getSubSkill())) {
                predicates.add(criteriaBuilder.equal(root.get("subSkill"), criteria.getSubSkill()));
            }

            // 5. Exact Match for Priority tier
            if (criteria.getPriority() != null && ! StringUtils.isBlank(criteria.getPriority())) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), criteria.getPriority()));
            }

            // 6. Exact Match for Difficulty Level
            if (criteria.getLevel() != null && !StringUtils.isBlank(criteria.getLevel())) {
                predicates.add(criteriaBuilder.equal(root.get("level"), criteria.getLevel()));
            }

            // 7. Optional Text Match on overall comments mapping to question text/labels
            if (criteria.getComments() != null && !StringUtils.isBlank(criteria.getComments())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("label")),
                        "%" + criteria.getComments().toLowerCase() + "%"
                ));
            }*/

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
