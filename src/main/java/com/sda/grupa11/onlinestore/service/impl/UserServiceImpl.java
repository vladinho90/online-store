package com.sda.grupa11.onlinestore.service.impl;


import com.sda.grupa11.onlinestore.model.*;
import com.sda.grupa11.onlinestore.model.enums.Role;
import com.sda.grupa11.onlinestore.model.enums.Status;
import com.sda.grupa11.onlinestore.repository.CartItemRepository;
import com.sda.grupa11.onlinestore.repository.CartRepository;
import com.sda.grupa11.onlinestore.repository.OrderRepository;
import com.sda.grupa11.onlinestore.repository.UserRepository;
import com.sda.grupa11.onlinestore.service.IProductService;
import com.sda.grupa11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public List<OrderLine>  checkout(User user){
        //Create an order
        Order order= new Order();
        BigDecimal orderPrice= BigDecimal.valueOf(0);
        order.setStatus(Status.NEW);
        List<OrderLine> orderLineList=new ArrayList<>();
        user.getCart().getCartItemSet().forEach(cartItem -> {
                OrderLine orderLine=new OrderLine();
                orderLine.setProduct(cartItem.getProduct());
                orderLine.setOrder(order);
                orderLine.setPrice(cartItem.getProduct().getPrice());
                orderLine.setQuantity(cartItem.getQuantity());
                cartItem.setCart(null);

                //trebuie sa facem o metoda care sa scada unitatile din stoc
                productService.decreaseStock(cartItem.getProduct().getId(),cartItem.getQuantity());
                cartItemRepository.save(cartItem);
                orderLineList.add(orderLine);
                orderPrice.add(orderLine.getPrice());

        });
        return orderLineList;
    }
}
