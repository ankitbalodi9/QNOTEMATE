package com.edtech.qnotemate.mapper;

import com.edtech.qnotemate.dto.UserDTO;
import com.edtech.qnotemate.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}






