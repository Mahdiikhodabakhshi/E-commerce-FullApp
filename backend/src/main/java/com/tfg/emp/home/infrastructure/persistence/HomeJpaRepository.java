package com.tfg.emp.home.infrastructure.persistence;

import com.tfg.emp.home.domain.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HomeJpaRepository extends JpaRepository<Home, Long>  , JpaSpecificationExecutor<Home> {
}
