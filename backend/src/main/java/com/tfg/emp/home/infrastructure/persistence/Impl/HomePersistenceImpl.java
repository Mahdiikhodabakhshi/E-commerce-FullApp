package com.tfg.emp.home.infrastructure.persistence.Impl;

import com.tfg.emp.home.domain.entity.Home;
import com.tfg.emp.home.domain.persistence.HomePersistence;
import com.tfg.emp.home.infrastructure.persistence.HomeJpaRepository;
import com.tfg.emp.home.specs.HomeSpecification;
import com.tfg.emp.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HomePersistenceImpl implements HomePersistence {
    private final HomeJpaRepository homeJpaRepository;

    @Autowired
    public HomePersistenceImpl(HomeJpaRepository homeJpaRepository) {
        this.homeJpaRepository = homeJpaRepository;
    }


    @Override
    public Page<Home> findAllHomes(Pageable pageable, String filter) {
        HomeSpecification specification = new HomeSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return homeJpaRepository.findAll(specification, pageable);
    }

    @Override
    public Optional<Home> getHomeById(Long homeId) {
        return homeJpaRepository.findById(homeId);
    }

    @Override
    public Home saveHome(Home home) {
        return homeJpaRepository.save(home);
    }

    @Override
    public void deleteHome(Long homeId) {
        homeJpaRepository.deleteById(homeId);
    }
}
