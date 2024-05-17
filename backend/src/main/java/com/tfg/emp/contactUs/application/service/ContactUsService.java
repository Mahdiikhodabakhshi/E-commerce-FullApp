package com.tfg.emp.contactUs.application.service;


import com.tfg.emp.contactUs.application.Dto.ContactUsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContactUsService {
    Page<ContactUsDto> getContactUsByCriteriaPaged(Pageable pageable , String search);
    Optional<ContactUsDto> getContactUsById(Long contactUsId);
    ContactUsDto saveContactUs(ContactUsDto contactUsDto);
    void deleteContactUsDto(Long contactUsId);
}
