package com.sda.grupa11.onlinestore.dto.orders;


import com.sda.grupa11.onlinestore.model.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {

    public OrdersResponse toDto(Orders orders) {
        OrdersResponse dto = new OrdersResponse();
        dto.setId(orders.getId());
        dto.setUser(orders.getUser());
        dto.setDeliveryAddress(orders.getDeliveryAddress());
        dto.setTotalPrice(orders.getTotalPrice());
        dto.setStatus(orders.getStatus());
        dto.setOrderLineList(orders.getOrderLineList());
        return dto;
    }

    public List<OrdersResponse> toDto(List<Orders> ordersList) {
        return ordersList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Orders toEntity(OrdersRequest ordersRequest) {
        Orders entity = new Orders();
        entity.setUser(ordersRequest.getUser());
        entity.setDeliveryAddress(ordersRequest.getDeliveryAddress());
        entity.setTotalPrice(ordersRequest.getTotalPrice());
        entity.setStatus(ordersRequest.getStatus());
        entity.setOrderLineList(ordersRequest.getOrderLineList());
        return entity;
    }

    public List<Orders> toEntity(List<OrdersRequest> ordersRequestList){
        List<Orders> ordersList = new ArrayList<>();
        for (OrdersRequest ordersRequest : ordersRequestList)
            ordersList.add(toEntity(ordersRequest));
        return ordersList;
    }
}
