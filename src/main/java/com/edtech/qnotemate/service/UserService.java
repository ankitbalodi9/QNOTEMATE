package com.edtech.qnotemate.service;

import com.edtech.qnotemate.dto.UserDTO;
import com.edtech.qnotemate.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(UserDTO userDTO);

    User getUserById(Long id);

    User getUserByUuid(UUID userUuid);

    List<User> getAllUsers();

    void updateUser(UserDTO userDTO);

    void deleteUser(Long id);

}
