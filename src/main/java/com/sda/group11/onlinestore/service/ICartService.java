package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.Cart;
import com.sda.group11.onlinestore.model.CartItem;
import com.sda.group11.onlinestore.model.OrderLine;
import com.sda.group11.onlinestore.model.User;

import java.util.List;

public interface ICartService {

    Cart getCart(User user);

    void addToCart(CartItem cartItem, User user);

    void deleteCart(Long cartItemId, User user);

    void delete(Long cartItemId, User user);

    public void saveCart(Cart cart);

    public List<OrderLine> checkout(Long cartId);

    void deleteCartById(Long id);

    Cart findCartById(Long id);
}
