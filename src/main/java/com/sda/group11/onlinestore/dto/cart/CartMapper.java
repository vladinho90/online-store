package com.sda.group11.onlinestore.dto.cart;

import com.sda.group11.onlinestore.dto.cart_item.CartItemMapper;
import com.sda.group11.onlinestore.dto.cart_item.CartItemResponse;
import com.sda.group11.onlinestore.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    public CartItemMapper cartItemMapper;

    public CartResponse toDto(Cart cart){
        CartResponse dto = new CartResponse();
        dto.setId(cart.getId());
        dto.setUser(cart.getUser());
        Set<CartItemResponse> cartItemResponses = cart.getCartItemSet()
                .stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet());
        dto.setCartItemResponses(cartItemResponses);
        return dto;
    }
}
