package com.tfg.emp.home.application.service.Impl;

import com.tfg.emp.home.application.Dto.HomeDto;
import com.tfg.emp.home.application.mapper.HomeMapper;
import com.tfg.emp.home.application.service.HomeService;
import com.tfg.emp.home.domain.entity.Home;
import com.tfg.emp.home.domain.persistence.HomePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    private final HomePersistence homePersistence;
    private final HomeMapper homeMapper;

    @Autowired
    public HomeServiceImpl(HomePersistence homePersistence, HomeMapper homeMapper) {
        this.homePersistence = homePersistence;
        this.homeMapper = homeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HomeDto> getHomeByCriteriaPaged(Pageable pageable, String search) {
        Page<Home> homePage = homePersistence.findAllHomes(pageable, search);
        return homePage.map(homeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HomeDto> getHomeById(Long homeId) {
        return homePersistence
                .getHomeById(homeId)
                .map(homeMapper::toDto);
    }

    @Override
    @Transactional
    public HomeDto saveHome(HomeDto homeDto) {
        homeDto.setUploadDate(new Date());
        Home home = homeMapper.toEntity(homeDto);
        home = homePersistence.saveHome(home);
        return homeMapper.toDto(home);
    }

    @Override
    public void deleteHome(Long homeId) {
        homePersistence.deleteHome(homeId);
    }
}
