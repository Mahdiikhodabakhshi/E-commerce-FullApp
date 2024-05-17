package com.tfg.emp.activity.infrastructure.persistence;

import com.tfg.emp.activity.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityJpaRepository extends JpaRepository<Activity, Long> , JpaSpecificationExecutor<Activity> {
    List<Activity> findByTitleContainsIgnoreCase(String name);
}
