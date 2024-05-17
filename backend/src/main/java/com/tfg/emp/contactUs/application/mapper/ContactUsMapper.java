package com.tfg.emp.contactUs.application.mapper;

import com.tfg.emp.contactUs.application.Dto.ContactUsDto;
import com.tfg.emp.contactUs.domain.entity.ContactUs;
import com.tfg.emp.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactUsMapper extends EntityMapper<ContactUsDto,ContactUs> {
}
