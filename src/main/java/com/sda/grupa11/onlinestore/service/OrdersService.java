package com.sda.grupa11.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.grupa11.onlinestore.dto.orders.OrdersMapper;
import com.sda.grupa11.onlinestore.dto.orders.OrdersRequest;
import com.sda.grupa11.onlinestore.dto.orders.OrdersResponse;
import com.sda.grupa11.onlinestore.model.Orders;
import com.sda.grupa11.onlinestore.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    private OrdersMapper ordersMapper;
    private ObjectMapper jacksonObjectMapper;

    //nu stiu daca e bine



    //pana aici

    public OrdersResponse findById(Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("orders not found"));
        return ordersMapper.toDto(orders);
    }

    public List<OrdersResponse> findAll() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersMapper.toDto(ordersList);
    }

    public OrdersResponse save(OrdersRequest ordersRequest) {
        Orders orders = ordersMapper.toEntity(ordersRequest);
        Orders savedOrders = ordersRepository.save(orders);
        return ordersMapper.toDto(savedOrders);
    }

    public OrdersResponse update(Long id, OrdersRequest ordersRequest) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("orders not found"));

        updateFields(orders, ordersRequest);

        Orders savedOrders = ordersRepository.save(orders);

        return ordersMapper.toDto(savedOrders);
    }

    public void updateFields(Orders orders, OrdersRequest ordersRequest) {
        orders.setUser(ordersRequest.getUser());
        orders.setDeliveryAddress(ordersRequest.getDeliveryAddress());
        orders.setTotalPrice(ordersRequest.getTotalPrice());
        orders.setStatus(ordersRequest.getStatus());
        orders.setOrderLineList(ordersRequest.getOrderLineList());
    }

    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }
}
