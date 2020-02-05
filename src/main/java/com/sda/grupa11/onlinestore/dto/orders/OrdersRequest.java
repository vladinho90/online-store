package com.sda.grupa11.onlinestore.dto.orders;

import com.sda.grupa11.onlinestore.model.Address;
import com.sda.grupa11.onlinestore.model.OrderLine;
import com.sda.grupa11.onlinestore.model.User;

import java.math.BigDecimal;
import java.util.List;

public class OrdersRequest {

    private User user;
    private Address deliveryAddress;
    private BigDecimal totalPrice;
    private String status;
    private List<OrderLine> orderLineList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
}
