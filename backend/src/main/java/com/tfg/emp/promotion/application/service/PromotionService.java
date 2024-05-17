package com.tfg.emp.promotion.application.service;


import com.tfg.emp.promotion.application.Dto.PromotionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PromotionService {
    Page<PromotionDTO> getPromotionByCriteriaPaged(Pageable pageable , String search);
    Optional<PromotionDTO> getPromotionById(Long promotionId);
    PromotionDTO savePromotion(PromotionDTO promotionDTO);
    void deletePromotion(Long promotionId);
}
