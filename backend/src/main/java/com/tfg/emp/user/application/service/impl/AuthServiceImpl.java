package com.tfg.emp.user.application.service.impl;


import com.tfg.emp.user.application.dto.UserDto;
import com.tfg.emp.user.application.mapper.UserMapper;
import com.tfg.emp.user.application.service.AuthService;
import com.tfg.emp.user.domain.entity.User;
import com.tfg.emp.user.domain.persistence.UserPersistence;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserPersistence userPersistence;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserPersistence userPersistence, UserMapper userMapper) {
        this.userPersistence = userPersistence;
        this.userMapper = userMapper;
    }

    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userPersistence.save(user));
    }

    @Override
    public Optional<UserDto> getUser(String username) {
        Optional<User> user = userPersistence.find(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.toDto(user.get()));
    }
}
