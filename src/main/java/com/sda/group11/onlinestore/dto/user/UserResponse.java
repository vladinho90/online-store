package com.sda.group11.onlinestore.dto.user;

import com.sda.group11.onlinestore.dto.orders.OrderResponse;
import com.sda.group11.onlinestore.model.Address;

public class UserResponse {

    private Long id;
    private String username;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
