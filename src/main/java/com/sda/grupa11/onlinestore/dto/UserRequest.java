package com.sda.grupa11.onlinestore.dto;

import com.sda.grupa11.onlinestore.model.Address;

public class UserRequest {

    private String username;
    private String password;
    private Address address;

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
}
