package com.sda.grupa11.onlinestore.controller;


import com.sda.grupa11.onlinestore.dto.orders.OrdersRequest;
import com.sda.grupa11.onlinestore.dto.orders.OrdersResponse;
import com.sda.grupa11.onlinestore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrdersResponse>> findAllOrders(){
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdersResponse> createOrder(@RequestBody OrdersRequest request) {
        return new ResponseEntity<>(ordersService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<OrdersResponse> getProductsById(@RequestParam(name = "id") Long id){
        OrdersResponse orders= ordersService.findById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        ordersService.deleteById(id);
        return new ResponseEntity<>("order deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersResponse> update(@PathVariable(name = "id") Long id,
                                                  @RequestBody OrdersRequest request) {
        OrdersResponse response = ordersService.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
