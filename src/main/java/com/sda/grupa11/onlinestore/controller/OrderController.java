package com.sda.grupa11.onlinestore.controller;


import com.sda.grupa11.onlinestore.model.Order;
import com.sda.grupa11.onlinestore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@RestController
//@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService ordersService;

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        return new ResponseEntity<>(ordersService.findAllOrders(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order request) {
//
//        return new ResponseEntity<>(ordersService.saveOrder(request),HttpStatus.OK);
//    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestParam(name = "orderId") Long id) {
        Order orders = ordersService.findOrderById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> delete(@PathVariable(name = "orderId") Long id, Principal principal) {
        principal.getName();
        ordersService.deleteOrderById(id);
        return new ResponseEntity<>("order deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable(name = "id") Long id,
                                        @RequestBody Order request) {
        Order response = ordersService.updateOrder(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
