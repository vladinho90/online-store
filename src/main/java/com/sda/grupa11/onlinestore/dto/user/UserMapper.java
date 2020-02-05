package com.sda.grupa11.onlinestore.dto.user;

import com.sda.grupa11.onlinestore.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toDto(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setAddress(user.getAddress());
        dto.setOrdersList(user.getOrdersList());
        return dto;
    }

    public List<UserResponse> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public User toEntity(UserRequest userRequest) {
        User entity = new User();
        entity.setUsername(userRequest.getUsername());
        entity.setPassword(userRequest.getPassword());
        entity.setAddress(userRequest.getAddress());
        entity.setOrdersList(userRequest.getOrdersList());
        return entity;
    }

    public List<User> toEntity(List<UserRequest> userRequests){
        List<User> users = new ArrayList<>();
        for (UserRequest userRequest : userRequests)
            users.add(toEntity(userRequest));
        return users;
    }
}
