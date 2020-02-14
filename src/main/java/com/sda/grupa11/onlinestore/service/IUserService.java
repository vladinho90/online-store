package com.sda.grupa11.onlinestore.service;


import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.model.enums.Role;

import java.util.List;

public interface IUserService {

    void saveUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User updateUser(Long id, User user);

    User findUserByUsername(String username);

    List<User> findUserByRole(Role role);

    User createUser(User user);
}
