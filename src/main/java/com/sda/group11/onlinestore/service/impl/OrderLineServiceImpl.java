package com.sda.group11.onlinestore.service.impl;


import com.sda.group11.onlinestore.service.IOrderLineService;
import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;
import com.sda.group11.onlinestore.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements IOrderLineService {

    @Autowired
    public OrderLineRepository orderLineRepository;

    @Override
    public void saveOrderLine(OrderLine orderLine){
        orderLineRepository.save(orderLine);
    }

    @Override
    public List<OrderLine> findAllOrderLines(){
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine findOrderLineById(Long id){
        return orderLineRepository.findById(id).orElseThrow(()->new RuntimeException("OrderLine with id: "+id+" not found"));
    }

    @Override
    public void deleteOrderLineById(Long id){
        orderLineRepository.deleteById(id);
    }

    @Override
    public OrderLine updateOrderLine(Long id, OrderLine orderLine){
        OrderLine orderLineUpdated = findOrderLineById(id);
        orderLineUpdated.setOrder(orderLine.getOrder());
        orderLineUpdated.setPrice(orderLine.getPrice());
        orderLineUpdated.setProduct(orderLine.getProduct());
        orderLineUpdated.setQuantity(orderLine.getQuantity());
        return orderLineUpdated;
    }

    @Override
    public List<OrderLine> findAllOrderLinesByOrder(Order order){
       return orderLineRepository.findAllByOrder(order);
    }
}
