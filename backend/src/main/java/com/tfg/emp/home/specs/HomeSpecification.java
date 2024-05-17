package com.tfg.emp.home.specs;

import com.tfg.emp.home.domain.entity.Home;
import com.tfg.emp.shared.EntitySpecification;
import com.tfg.emp.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class HomeSpecification extends EntitySpecification<Home> implements Specification<Home> {


    public HomeSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
