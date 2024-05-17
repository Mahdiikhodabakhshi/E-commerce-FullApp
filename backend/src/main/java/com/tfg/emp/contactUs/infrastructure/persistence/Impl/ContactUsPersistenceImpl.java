package com.tfg.emp.contactUs.infrastructure.persistence.Impl;

import com.tfg.emp.contactUs.domain.entity.ContactUs;
import com.tfg.emp.contactUs.domain.persistence.ContactUsPersistence;
import com.tfg.emp.contactUs.infrastructure.persistence.ContactUsJpaRepository;
import com.tfg.emp.contactUs.specs.ContactUsSpecification;
import com.tfg.emp.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ContactUsPersistenceImpl implements ContactUsPersistence {
    private final ContactUsJpaRepository contactUsJpaRepository;

    @Autowired
    public ContactUsPersistenceImpl(ContactUsJpaRepository contactUsJpaRepository) {
        this.contactUsJpaRepository = contactUsJpaRepository;
    }

    @Override
    public Page<ContactUs> findAllContactUs(Pageable pageable, String filter) {
        ContactUsSpecification specification = new ContactUsSpecification(SearchCriteriaHelper.fromFilterString(filter));

        return contactUsJpaRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<ContactUs> getContactUsById(Long contactUsId) {
        return contactUsJpaRepository.findById(contactUsId);
    }

    @Override
    public ContactUs saveContactUs(ContactUs contactUs) {
        return contactUsJpaRepository.save(contactUs);
    }

    @Override
    public void deleteContactUs(Long contactUsId) {
        contactUsJpaRepository.deleteById(contactUsId);
    }
}
