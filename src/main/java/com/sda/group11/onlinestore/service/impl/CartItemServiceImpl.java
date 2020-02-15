package com.sda.group11.onlinestore.service.impl;


import com.sda.group11.onlinestore.model.CartItem;
import com.sda.group11.onlinestore.repository.CartItemRepository;
import com.sda.group11.onlinestore.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements ICartItemService {

    @Autowired
    public CartItemRepository cartItemRepository;

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow((()->new RuntimeException("CartItem with id: "+id+" not found")));
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }

    //nu cred ca poti schimba cartul , sau produsul
    @Override
    public CartItem updateCartItem(Long id, CartItem cartItem) {
       CartItem crtItem=findCartItemById(id);
       crtItem.setCart(cartItem.getCart());
       crtItem.setProduct(cartItem.getProduct());
       crtItem.setQuantity(cartItem.getQuantity());
       return crtItem;
    }
}
