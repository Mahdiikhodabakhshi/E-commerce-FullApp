package com.tfg.emp.contactUs.domain.persistence;


import com.tfg.emp.contactUs.domain.entity.ContactUs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContactUsPersistence {
    Page<ContactUs> findAllContactUs(Pageable pageable, String filter);
    Optional<ContactUs> getContactUsById(Long contactUsId);
    ContactUs saveContactUs(ContactUs contactUs);
    void deleteContactUs(Long contactUsId);
}
