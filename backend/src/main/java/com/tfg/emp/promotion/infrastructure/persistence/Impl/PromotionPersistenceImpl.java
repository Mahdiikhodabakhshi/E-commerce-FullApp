package com.tfg.emp.promotion.infrastructure.persistence.Impl;

import com.tfg.emp.promotion.domain.entity.Promotion;
import com.tfg.emp.promotion.domain.persistence.PromotionPersistence;
import com.tfg.emp.promotion.infrastructure.persistence.PromotionJpaRepository;
import com.tfg.emp.promotion.specs.PromotionSpecification;
import com.tfg.emp.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PromotionPersistenceImpl implements PromotionPersistence {

    private  final PromotionJpaRepository promotionJpaRepository;

    @Autowired
    public PromotionPersistenceImpl(PromotionJpaRepository promotionJpaRepository) {
        this.promotionJpaRepository = promotionJpaRepository;
    }

    @Override
    public Page<Promotion> findAllPromotions(Pageable pageable, String filter) {
        PromotionSpecification specification = new PromotionSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return promotionJpaRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Promotion> getPromotionById(Long promotionId) {
        return promotionJpaRepository.findById(promotionId);
    }

    @Override
    public Promotion savePromotion(Promotion promotion) {
        return promotionJpaRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long promotionId) {
        promotionJpaRepository.deleteById(promotionId);
    }
}
