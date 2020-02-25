package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllUsers();

    void save(User user);

    List<User> findAll();

    User findById(Long id);

    User update(Long id, User user);

    void delete(Long id);

}
