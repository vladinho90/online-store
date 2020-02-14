package com.sda.grupa11.onlinestore.dto.user;

import com.sda.grupa11.onlinestore.model.Address;
import com.sda.grupa11.onlinestore.model.Order;


import java.util.List;

public class UserResponse {

    private Long id;
    private String username;
    private Address address;
    private List<Order> ordersList;

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

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }
}
