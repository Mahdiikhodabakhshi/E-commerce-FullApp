package com.tfg.emp.promotion.domain.persistence;

import com.tfg.emp.promotion.domain.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface PromotionPersistence {
    Page<Promotion> findAllPromotions(Pageable pageable, String filter);
    Optional<Promotion> getPromotionById(Long promotionId);
    Promotion savePromotion(Promotion promotion);
    void deletePromotion(Long promotionId);
}
