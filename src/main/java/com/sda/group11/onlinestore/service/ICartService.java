package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {

    Cart getCart(User user);

    void addToCart(CartItem cartItem, User user);

    void deleteCart(Long cartItemId, User user);

    void delete(Long cartItemId, User user);

    void saveCart(Cart cart);

    Order checkout(Long cartId);

    void deleteCartById(Long id);

    Cart findCartById(Long id);

    BigDecimal getPricePerOrderLine (CartItem cartItem);
}
