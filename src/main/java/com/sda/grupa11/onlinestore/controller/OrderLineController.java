package com.sda.grupa11.onlinestore.controller;


import com.sda.grupa11.onlinestore.model.Order;
import com.sda.grupa11.onlinestore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller("/orders")
public class OrderLineController {

    @Autowired
    public com.sda.grupa11.onlinestore.service.IUserService IUserService;
    @Autowired
    public com.sda.grupa11.onlinestore.service.IOrderService IOrderService;
    @Autowired
    public com.sda.grupa11.onlinestore.service.IOrderLineService IOrderLineService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrdersByUser(@RequestParam(name = "orderNumber") Long orderId, Principal principal){
        User user= IUserService.findUserByUsername(principal.getName());
        return ResponseEntity.ok(IOrderService.findAllOrdersByUser(user));
    }


}
