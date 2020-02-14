package com.sda.grupa11.onlinestore.service;

import com.sda.grupa11.onlinestore.model.Cart;
import com.sda.grupa11.onlinestore.model.CartItem;
import com.sda.grupa11.onlinestore.model.User;

import java.util.Collection;
import java.util.List;

public interface ICartService {

    Cart getCart(User user);

    void addToCart(CartItem cartItem, User user);

    void deleteCart(Long cartItemId, User user);



    //cum transformam cartul in order

}
