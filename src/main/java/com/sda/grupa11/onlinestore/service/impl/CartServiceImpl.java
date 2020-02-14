package com.sda.grupa11.onlinestore.service.impl;


import com.sda.grupa11.onlinestore.model.Cart;
import com.sda.grupa11.onlinestore.repository.CartRepository;
import com.sda.grupa11.onlinestore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    public CartRepository cartRepository;

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(()->new RuntimeException("Cart with id: "+id+" not found"));
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    //nu ai avea cum sa schimbi un car de la un user la altu
    @Override
    public Cart updateCart(Long id, Cart cart) {
        Cart crt=findCartById(id);
        crt.setCartItemSet(cart.getCartItemSet());
        return crt;
    }
}
