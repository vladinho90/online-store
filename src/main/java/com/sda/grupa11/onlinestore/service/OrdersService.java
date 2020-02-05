package com.sda.grupa11.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.grupa11.onlinestore.dto.orders.OrdersMapper;
import com.sda.grupa11.onlinestore.dto.orders.OrdersRequest;
import com.sda.grupa11.onlinestore.dto.orders.OrdersResponse;
import com.sda.grupa11.onlinestore.model.Orders;
import com.sda.grupa11.onlinestore.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final static Logger log = LoggerFactory.getLogger(OrdersService.class);
    private OrdersRepository ordersRepository;
    private OrdersMapper ordersMapper;
    private ObjectMapper jacksonObjectMapper;

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

    public OrdersResponse update(OrdersRequest ordersRequest, Long id) {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("orders not found"));

        updateFields(orders, ordersRequest);

        Orders savedOrders = ordersRepository.save(orders);

        return ordersMapper.toDto(savedOrders);
    }

    private void updateFields(Orders orders, OrdersRequest ordersRequest) {
        orders.setUser(ordersRequest.getUser());
        orders.setDeliveryAddress(ordersRequest.getDeliveryAddress());
        orders.setTotalPrice(ordersRequest.getTotalPrice());
        orders.setStatus(ordersRequest.getStatus());
        orders.setOrderLineList(ordersRequest.getOrderLineList());
    }

    private String deleteById(Long id) {
        ordersRepository.deleteById(id);
        return "orders deleted successfully";
    }
}
