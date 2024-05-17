package com.tfg.emp.contactUs.specs;




import com.tfg.emp.contactUs.domain.entity.ContactUs;
import com.tfg.emp.shared.EntitySpecification;
import com.tfg.emp.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ContactUsSpecification extends EntitySpecification<ContactUs> implements Specification<ContactUs> {


    public ContactUsSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}
