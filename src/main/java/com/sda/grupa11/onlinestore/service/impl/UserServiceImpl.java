package com.sda.grupa11.onlinestore.service.impl;


import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.repository.UserRepository;
import com.sda.grupa11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //nu prea avem cum sa schimbam username ul
    @Override
    public User updateUser(Long id, User user) {
        User userUpdate = findUserById(id);
        userUpdate.setAddress(user.getAddress());
        userUpdate.setOrdersList(user.getOrdersList());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setRole(user.getRole());
        userUpdate.setUsername(user.getUsername());


        return null;
    }

    @Override
    public User findUserByUsername(String username) {
       return userRepository.findUserByUsername(username);
    }
}
