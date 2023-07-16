package com.edtech.qnotemate.serviceImpl;

import com.edtech.qnotemate.dto.UserDTO;
import com.edtech.qnotemate.entity.User;
import com.edtech.qnotemate.exception.UserNotFoundException;
import com.edtech.qnotemate.repository.UserRepository;
import com.edtech.qnotemate.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        // Check if the username already exists
        if (isUsernameExists(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDTO.getUsername());
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUserUuid(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User getUserByUuid(UUID userUuid) {
        return userRepository.findByUserUuid(userUuid).orElseThrow(() -> new UserNotFoundException("User not found with UUID: " + userUuid));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + userDTO.getUsername()));

        // Update the user entity with the new data from the DTO
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    private boolean isUsernameExists(String username) {
        return userRepository.existsByUserName(username);
    }

}
