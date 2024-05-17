package com.tfg.emp.user.infraestructure.persistence;


import com.tfg.emp.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {

}
