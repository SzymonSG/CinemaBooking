package com.cinema.booking.mappers;

import com.cinema.booking.dtos.userDto.UserModelDto;
import com.cinema.booking.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {


    UserModelDto toUserModelDto(User user);
    User dtoToUser (UserModelDto userModelDto);



}
