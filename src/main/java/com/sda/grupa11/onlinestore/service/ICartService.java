package com.sda.grupa11.onlinestore.service;

import com.sda.grupa11.onlinestore.model.Cart;

import java.util.List;

public interface ICartService {

    void saveCart(Cart cart);

    List<Cart> findAllCarts();

    Cart findCartById(Long id);

    void deleteCartById(Long id);

    Cart updateCart(Long id, Cart Cart);

}
