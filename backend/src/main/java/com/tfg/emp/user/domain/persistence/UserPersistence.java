package com.tfg.emp.user.domain.persistence;


import com.tfg.emp.user.domain.entity.User;

import java.util.Optional;

public interface UserPersistence {
    User save(User user);
    Optional<User> find(String username);
}
