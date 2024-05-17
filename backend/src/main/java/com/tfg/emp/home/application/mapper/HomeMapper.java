package com.tfg.emp.home.application.mapper;

import com.tfg.emp.home.application.Dto.HomeDto;
import com.tfg.emp.home.domain.entity.Home;
import com.tfg.emp.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeMapper extends EntityMapper<HomeDto,Home> {
}
