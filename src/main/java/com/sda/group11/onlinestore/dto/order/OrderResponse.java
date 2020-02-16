package com.sda.group11.onlinestore.dto.order;

import com.sda.group11.onlinestore.dto.order_line.OrderLineResponse;
import com.sda.group11.onlinestore.model.enums.Status;
import com.sda.group11.onlinestore.model.Address;
import com.sda.group11.onlinestore.model.User;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private Long id;
    private User user;
    private Address deliveryAddress;
    private BigDecimal totalPrice;
    private Status status;
    private List<OrderLineResponse> orderLineResponsesList;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderLineResponse> getOrderLineResponsesList() {
        return orderLineResponsesList;
    }

    public void setOrderLineResponsesList(List<OrderLineResponse> orderLineResponsesList) {
        this.orderLineResponsesList = orderLineResponsesList;
    }
}
