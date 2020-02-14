package com.sda.grupa11.onlinestore.service;


import com.sda.grupa11.onlinestore.model.User;

import java.util.List;

public interface IUserService {

    void saveUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    void deleteUserById(Long id);

    User updateUser(Long id, User user);

    User findUserByUsername(String username);
}
