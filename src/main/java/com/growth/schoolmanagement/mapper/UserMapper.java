package com.growth.schoolmanagement.mapper;

import com.growth.schoolmanagement.domain.User;
import com.growth.schoolmanagement.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> user);
}
