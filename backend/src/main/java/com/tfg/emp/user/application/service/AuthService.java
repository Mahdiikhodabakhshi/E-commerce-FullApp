package com.tfg.emp.user.application.service;


import com.tfg.emp.user.application.dto.UserDto;

import java.util.Optional;

public interface AuthService {
    public UserDto register(UserDto userDto);
    Optional<UserDto> getUser(String username);
}
