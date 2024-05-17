package com.tfg.emp.contactUs.infrastructure.persistence;

import com.tfg.emp.contactUs.domain.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContactUsJpaRepository extends JpaRepository<ContactUs, Long> , JpaSpecificationExecutor<ContactUs> {
}
