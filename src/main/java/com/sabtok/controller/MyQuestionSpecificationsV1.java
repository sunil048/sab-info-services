package com.sabtok.controller;

import com.sabtok.dto.MyTestSearchRequest;
import com.sabtok.entity.MyQuestion;
import com.sabtok.entity.MyTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MyQuestionSpecificationsV1 {

    public static Specification<MyQuestion> getDynamicSearchSpecification(MyTestSearchRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            // 2. Multiple Values Match (WHERE category IN (...))
            if (request.getCategories() != null && !request.getCategories().isEmpty()) {
                predicates.add(root.get("category").in(request.getCategories()));
            }

            // 3. Multiple Values Match (WHERE priority IN (...))
            if (request.getPriorities() != null && !request.getPriorities().isEmpty()) {
                predicates.add(root.get("priority").in(request.getPriorities()));
            }

            // 4. Multiple Values Match (WHERE level IN (...))
            if (request.getLevels() != null && !request.getLevels().isEmpty()) {
                predicates.add(root.get("level").in(request.getLevels()));
            }

            // Combine all active predicates with an AND condition
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
