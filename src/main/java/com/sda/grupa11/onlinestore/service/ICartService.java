package com.sda.grupa11.onlinestore.service;

import com.sda.grupa11.onlinestore.model.Cart;
import com.sda.grupa11.onlinestore.model.CartItem;
import com.sda.grupa11.onlinestore.model.User;

public interface ICartService {

    Cart getCart(User user);

    void addToCart(CartItem cartItem, User user);

    void deleteCart(Long cartItemId, User user);

    void delete(Long cartItemId, User user);

    public void saveCart(Cart cart);

}
