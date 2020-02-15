package com.sda.group11.onlinestore.service;


import com.sda.group11.onlinestore.model.enums.Status;
import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;
import com.sda.group11.onlinestore.model.Product;
import com.sda.group11.onlinestore.model.User;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void saveOrder(Order order);

    List<Order> findAllOrders();

    Order findOrderById(Long id);

    void deleteOrderById(Long id);

    Order updateOrder(Long id, Order order);

    List<Order> findAllOrdersByUser(User user);

    OrderLine createOrderLine(Product product, Order order, Integer quantity);

    Optional<Order> findOrderByStatus(Status status);
}
