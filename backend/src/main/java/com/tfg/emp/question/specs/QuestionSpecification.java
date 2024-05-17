package com.tfg.emp.question.specs;

import com.tfg.emp.question.domain.entity.Question;
import com.tfg.emp.shared.EntitySpecification;
import com.tfg.emp.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class QuestionSpecification extends EntitySpecification<Question> implements Specification<Question> {


    public QuestionSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
