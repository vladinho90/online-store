package com.sda.grupa11.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.grupa11.onlinestore.dto.UserMapper;
import com.sda.grupa11.onlinestore.dto.UserRequest;
import com.sda.grupa11.onlinestore.dto.UserResponse;
import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private UserMapper userMapper;
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, ObjectMapper jacksonObjectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }


    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("user not found"));
        return userMapper.toDto(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public UserResponse save(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("user not found"));

        updateFields(userRequest, user);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    private void updateFields(UserRequest userRequest, User user) {
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setAddress(userRequest.getAddress());
    }

    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return "user deleted successfully";
    }
}
