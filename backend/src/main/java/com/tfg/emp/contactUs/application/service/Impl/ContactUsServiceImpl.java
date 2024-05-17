package com.tfg.emp.contactUs.application.service.Impl;

import com.tfg.emp.contactUs.application.Dto.ContactUsDto;
import com.tfg.emp.contactUs.application.mapper.ContactUsMapper;
import com.tfg.emp.contactUs.application.service.ContactUsService;
import com.tfg.emp.contactUs.domain.entity.ContactUs;
import com.tfg.emp.contactUs.domain.persistence.ContactUsPersistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ContactUsServiceImpl implements ContactUsService {
    private final ContactUsPersistence contactUsPersistence;
    private final ContactUsMapper contactUsMapper;

    public ContactUsServiceImpl(ContactUsPersistence contactUsPersistence, ContactUsMapper contactUsMapper) {
        this.contactUsPersistence = contactUsPersistence;
        this.contactUsMapper = contactUsMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContactUsDto> getContactUsByCriteriaPaged(Pageable pageable, String search) {
        Page<ContactUs> contactUses = contactUsPersistence.findAllContactUs(pageable, search);

        return contactUses.map(contactUsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactUsDto> getContactUsById(Long contactUsId) {
        return contactUsPersistence
                .getContactUsById(contactUsId)
                .map(contactUsMapper::toDto);
    }

    @Override
    @Transactional
    public ContactUsDto saveContactUs(ContactUsDto contactUsDto) {
        contactUsDto.setSendDate(new Date());
        ContactUs contactUs = contactUsMapper.toEntity(contactUsDto);
        contactUs = contactUsPersistence.saveContactUs(contactUs);
        return contactUsMapper.toDto(contactUs);
    }

    @Override
    public void deleteContactUsDto(Long contactUsId) {
        contactUsPersistence.deleteContactUs(contactUsId);
    }
}
