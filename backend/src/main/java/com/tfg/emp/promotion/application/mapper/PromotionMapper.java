package com.tfg.emp.promotion.application.mapper;

import com.tfg.emp.mapper.EntityMapper;
import com.tfg.emp.promotion.application.Dto.PromotionDTO;
import com.tfg.emp.promotion.domain.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends EntityMapper<PromotionDTO,Promotion> {
}
