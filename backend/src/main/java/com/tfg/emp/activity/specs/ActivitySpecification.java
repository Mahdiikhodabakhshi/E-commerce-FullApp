package com.tfg.emp.activity.specs;


import com.tfg.emp.activity.domain.entity.Activity;
import com.tfg.emp.shared.EntitySpecification;
import com.tfg.emp.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ActivitySpecification extends EntitySpecification<Activity> implements Specification<Activity> {


    public ActivitySpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
