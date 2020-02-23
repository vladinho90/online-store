package com.sda.group11.onlinestore.service.impl;


import com.sda.group11.onlinestore.model.Cart;
import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.repository.CartItemRepository;
import com.sda.group11.onlinestore.repository.CartRepository;
import com.sda.group11.onlinestore.repository.OrderRepository;
import com.sda.group11.onlinestore.repository.UserRepository;
import com.sda.group11.onlinestore.service.IProductService;
import com.sda.group11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public IProductService productService;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //nu prea avem cum sa schimbam username ul
    @Override
    public User update(Long id, User user) {
        User userUpdate = findById(id).orElseThrow( () -> new RuntimeException("not found"));
        userUpdate.setAddress(user.getAddress());
        userUpdate.setOrdersList(user.getOrdersList());
       // userUpdate.setPassword(user.getPassword());
        userUpdate.setRole(user.getRole());
        userUpdate.setUsername(user.getUsername());
        userUpdate.setFullName(user.getFullName());
        return userRepository.save(userUpdate);
    }

    @Override
    public Optional<User> findUserByUsername(String username)
    {
       return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findUserByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public User createUser(User user) {

        User newUser = userRepository.save(user);
        //create cart
        Cart newCart = cartRepository.save(new Cart());
        newCart.setUser(newUser);
        return userRepository.save(newUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
