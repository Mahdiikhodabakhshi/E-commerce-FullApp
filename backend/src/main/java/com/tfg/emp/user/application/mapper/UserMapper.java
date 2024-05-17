package com.tfg.emp.user.application.mapper;


import com.tfg.emp.mapper.EntityMapper;
import com.tfg.emp.user.application.dto.UserDto;
import com.tfg.emp.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    default User fromId(String email) {
        if (email == null) {
            return null;
        }
        User user = new User();
        user.setEmail(email);
        return user;
    }
}
