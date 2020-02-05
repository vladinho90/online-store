package com.sda.grupa11.onlinestore.dto.user;

import com.sda.grupa11.onlinestore.model.Address;
import com.sda.grupa11.onlinestore.model.Orders;

import java.util.List;

public class UserRequest {

    private String username;
    private String password;
    private Address address;
    private List<Orders> ordersList;

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

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
