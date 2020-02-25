package com.sda.group11.onlinestore.service;


import com.sda.group11.onlinestore.model.enums.Status;
import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;
import com.sda.group11.onlinestore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RandomService {

    @Autowired
    IOrderService IOrderService;

    @Autowired
    IOrderLineService IOrderLineService;

    public OrderLine createOrderLine(Product product, Order order) {
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setPrice(product.getPrice());
        orderLine.setOrder(order);
        //cand adaugam in cos scadem o unitate din stoc
        product.setUnitsInStock(product.getUnitsInStock() - 1);
        if (product.getUnitsInStock() == 0) {
            product.setStock(false);
        }
        IOrderLineService.save(orderLine);
        return orderLine;
    }

    public Order createOrderNewIfNotExist() {
        Optional<Order> orderOptional = IOrderService.findOrderByStatus(Status.NEW);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            Order order = new Order();
            IOrderService.save(order);
            return order;
        }
    }

    public List<OrderLine> addNewOrderLineToOrderList(OrderLine orderLine) {
        Order order = createOrderNewIfNotExist();
        List<OrderLine> orderLineList = order.getOrderLineList();
        if (orderLineList.contains(orderLine)) {
            orderLine.setQuantity(orderLine.getQuantity() + 1);
        } else {
            orderLineList.add(orderLine);
        }
        return orderLineList;
    }

    public void setOrderStatusCanceled(Order order) {
        order.setStatus(Status.CANCELED);
    }


    public void setOrderStatusFinished(Order order) {
        order.setStatus(Status.FINISHED);
    }

    public BigDecimal setOrderTotalPrice(Order order) {
        BigDecimal finalPrice = BigDecimal.valueOf(0);
        List<OrderLine> orderLineList = order.getOrderLineList();
        for (OrderLine orderLine : orderLineList) {
            finalPrice = finalPrice.add(orderLine.getPrice());
        }
        return finalPrice;
    }


    public void deleteOrderLineFromOrder(Order order, OrderLine orderLine) {
        List<OrderLine> orderLineList = IOrderLineService.findAllOrderLinesByOrder(order);
        orderLineList.remove(orderLine);
        Product product = orderLine.getProduct();
        if (product.getUnitsInStock() == 0) {
            product.setStock(true);
            product.setUnitsInStock(orderLine.getProduct().getUnitsInStock() + 1);
        }
        product.setUnitsInStock(orderLine.getProduct().getUnitsInStock() + 1);
        IOrderLineService.delete(orderLine.getId());
    }
}
