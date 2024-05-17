package com.tfg.emp.promotion.application.service.Impl;

import com.tfg.emp.promotion.application.Dto.PromotionDTO;
import com.tfg.emp.promotion.application.mapper.PromotionMapper;
import com.tfg.emp.promotion.application.service.PromotionService;
import com.tfg.emp.promotion.domain.entity.Promotion;
import com.tfg.emp.promotion.domain.persistence.PromotionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionPersistence promotionPersistence;
    private final PromotionMapper promotionMapper;

    @Autowired
    public PromotionServiceImpl(PromotionPersistence promotionPersistence, PromotionMapper promotionMapper) {
        this.promotionPersistence = promotionPersistence;
        this.promotionMapper = promotionMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<PromotionDTO> getPromotionByCriteriaPaged(Pageable pageable, String search) {
        Page<Promotion> promotions = promotionPersistence.findAllPromotions(pageable, search);
        return promotions.map(promotionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PromotionDTO> getPromotionById(Long promotionId) {
        return promotionPersistence
                .getPromotionById(promotionId)
                .map(promotionMapper::toDto);
    }

    @Override
    @Transactional
    public PromotionDTO savePromotion(PromotionDTO promotionDTO) {
        promotionDTO.setUploadDate(new Date());
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion = promotionPersistence.savePromotion(promotion);
        return promotionMapper.toDto(promotion);
    }

    @Override
    public void deletePromotion(Long promotionId) {
        promotionPersistence.deletePromotion(promotionId);
    }
}
