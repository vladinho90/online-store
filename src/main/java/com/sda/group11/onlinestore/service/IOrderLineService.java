package com.sda.group11.onlinestore.service;


import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;

import java.util.List;

public interface IOrderLineService {

    void saveOrderLine(OrderLine orderLine);

    List<OrderLine> findAllOrderLines();

    OrderLine findOrderLineById(Long id);

    void deleteOrderLineById(Long id);

    OrderLine updateOrderLine(Long id, OrderLine orderLine);

    List<OrderLine> findAllOrderLinesByOrder(Order order);


}
