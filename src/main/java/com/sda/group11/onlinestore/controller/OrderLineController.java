package com.sda.group11.onlinestore.controller;


import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

//@RestController
//@RequestMapping("/api/order")
public class OrderLineController {

    //TODO nu stiu cata nevoie vom avea de acest controller
    @Autowired
    public com.sda.group11.onlinestore.service.IUserService IUserService;
    @Autowired
    public com.sda.group11.onlinestore.service.IOrderService IOrderService;
    @Autowired
    public com.sda.group11.onlinestore.service.IOrderLineService IOrderLineService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrdersByUser(@RequestParam(name = "orderNumber") Long orderId, Principal principal){
        User user= IUserService.findUserByUsername(principal.getName());
        return ResponseEntity.ok(IOrderService.findAllOrdersByUser(user));
    }


}
