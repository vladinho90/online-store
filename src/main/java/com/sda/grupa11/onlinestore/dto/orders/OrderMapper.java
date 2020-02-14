package com.sda.grupa11.onlinestore.dto.orders;

import com.sda.grupa11.onlinestore.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public OrderResponse toDto(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setUser(order.getUser());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setOrderLineList(order.getOrderLineList());
        return dto;
    }

    public List<OrderResponse> toDto(List<Order> orderList) {
        return orderList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Order toEntity(OrderRequest orderRequest) {
        Order entity = new Order();
        entity.setUser(orderRequest.getUser());
        entity.setDeliveryAddress(orderRequest.getDeliveryAddress());
        entity.setTotalPrice(orderRequest.getTotalPrice());
        entity.setStatus(orderRequest.getStatus());
        entity.setOrderLineList(orderRequest.getOrderLineList());
        return entity;
    }

    public List<Order> toEntity(List<OrderRequest> orderRequestList){
        List<Order> ordersList = new ArrayList<>();
        for (OrderRequest orderRequest : orderRequestList)
            ordersList.add(toEntity(orderRequest));
        return ordersList;
    }
}
