package com.sda.group11.onlinestore.service;


import com.sda.group11.onlinestore.model.CartItem;

import java.util.List;

public interface ICartItemService {


    void save(CartItem cartItem);

    List<CartItem> findAll();

    CartItem findById(Long id);

    void delete(Long id);

    CartItem update(Long id, CartItem cartItem);
}
