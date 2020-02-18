package com.sda.group11.onlinestore.dto.cart;

import com.sda.group11.onlinestore.dto.cart_item.CartItemResponse;
import com.sda.group11.onlinestore.model.CartItem;
import com.sda.group11.onlinestore.model.User;
import java.util.Set;

public class CartResponse {

    private Long id;
    private User user;
    private Set<CartItemResponse> cartItemResponses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItemResponse> getCartItemResponses() {
        return cartItemResponses;
    }

    public void setCartItemResponses(Set<CartItemResponse> cartItemResponses) {
        this.cartItemResponses = cartItemResponses;
    }
}
