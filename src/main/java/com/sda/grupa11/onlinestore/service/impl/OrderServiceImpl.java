package com.sda.grupa11.onlinestore.service.impl;

import com.sda.grupa11.onlinestore.model.Order;
import com.sda.grupa11.onlinestore.model.OrderLine;
import com.sda.grupa11.onlinestore.model.Product;
import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.model.enums.Status;
import com.sda.grupa11.onlinestore.repository.OrderRepository;
import com.sda.grupa11.onlinestore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Override
    public void saveOrder (Order order){
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order with id: "+id+" not found"));
    }

    @Override
    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(Long id, Order order){
        Order orderUpdated= findOrderById(id);
        orderUpdated.setDeliveryAddress(order.getDeliveryAddress());
        orderUpdated.setOrderLineList(order.getOrderLineList());
        orderUpdated.setStatus(order.getStatus());
        orderUpdated.setTotalPrice(order.getTotalPrice());
        return orderUpdated;
    }

    @Override
    public List<Order> findAllOrdersByUser(User user){
       return orderRepository.findAllByUser(user);
    }

    //trebuie verificat
    @Override
    public OrderLine createOrderLine(Product product, Order order, Integer quantity){
        OrderLine orderLine=new OrderLine();
        orderLine.setProduct(product);
        orderLine.setPrice(product.getPrice());
        orderLine.setOrder(order);
        orderLine.setQuantity(quantity);
        return orderLine;
    }

    @Override
    public Optional<Order> findOrderByStatus(Status status) {
        return orderRepository.findAllByStatus(status);
    }
}
