package com.tfg.emp.home.domain.persistence;


import com.tfg.emp.home.domain.entity.Home;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HomePersistence {
    Page<Home> findAllHomes(Pageable pageable, String filter);
    Optional<Home> getHomeById(Long homeId);
    Home saveHome(Home home);
    void deleteHome(Long homeId);
}
