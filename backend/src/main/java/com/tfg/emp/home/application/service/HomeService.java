package com.tfg.emp.home.application.service;


import com.tfg.emp.home.application.Dto.HomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HomeService {
    Page<HomeDto> getHomeByCriteriaPaged(Pageable pageable , String search);
    Optional<HomeDto> getHomeById(Long homeId);
    HomeDto saveHome(HomeDto homeDto);
    void deleteHome(Long homeId);
}
