package com.sda.grupa11.onlinestore.dto.user;

import com.sda.grupa11.onlinestore.model.Address;
import com.sda.grupa11.onlinestore.model.Order;

import java.util.List;

public class UserRequest {

    private String username;
    private String password;
    private Address address;
    private List<Order> ordersList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
