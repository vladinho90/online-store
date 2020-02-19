package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.model.User;

import java.util.List;

public interface IUserService {

    void save(User user);

    List<User> findAll();

    User findById(Long id);

    User update(Long id, User user);

    User findUserByUsername(String username);

    List<User> findUserByRole(Role role);

    User createUser(User user);

    void delete(Long id);
}
