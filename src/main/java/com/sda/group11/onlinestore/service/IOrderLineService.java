package com.sda.group11.onlinestore.service;

import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;

import java.util.List;

public interface IOrderLineService {

    void save(OrderLine orderLine);

    List<OrderLine> findAll();

    OrderLine findById(Long id);

    void delete(Long id);

    OrderLine update(Long id, OrderLine orderLine);

    List<OrderLine> findAllOrderLinesByOrder(Order order);
}
