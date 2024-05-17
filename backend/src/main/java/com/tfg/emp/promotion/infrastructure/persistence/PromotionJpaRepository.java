package com.tfg.emp.promotion.infrastructure.persistence;

import com.tfg.emp.promotion.domain.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromotionJpaRepository extends JpaRepository<Promotion, Long> , JpaSpecificationExecutor<Promotion> {
}
