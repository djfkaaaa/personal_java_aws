package com.james.api.user.service;


import com.james.api.common.BaseEntitiy;
import com.james.api.common.command.CommandService;
import com.james.api.common.component.MessengerVo;
import com.james.api.common.component.PageRequestVo;
import com.james.api.common.query.QueryService;
import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.james.api.common.BaseEntitiy;

public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {



    MessengerVo modify(UserDto dto);
    List<UserDto> findUserByName(String name);
    List<UserDto> findUserByJob(String job);
    Optional<User> findUserByUsername(String username);
    MessengerVo login(UserDto user);


    default User dtoToEntity(UserDto dto){
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .job(dto.getJob())
                .build();
    }
    default UserDto entityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .job(user.getJob())
                .build();
    }
}
