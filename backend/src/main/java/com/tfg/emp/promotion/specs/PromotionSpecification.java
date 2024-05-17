package com.tfg.emp.promotion.specs;



import com.tfg.emp.promotion.domain.entity.Promotion;
import com.tfg.emp.shared.EntitySpecification;
import com.tfg.emp.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PromotionSpecification extends EntitySpecification<Promotion> implements Specification<Promotion> {


    public PromotionSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
