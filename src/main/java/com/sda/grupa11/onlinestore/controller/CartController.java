package com.sda.grupa11.onlinestore.controller;


import com.sda.grupa11.onlinestore.dto.order_line.OrderLineResponse;
import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.repository.OrderLineRepository;
import com.sda.grupa11.onlinestore.service.IOrderLineService;
import com.sda.grupa11.onlinestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    IUserService userService;

    @Autowired
    IOrderLineService orderLineService;

    @Autowired
    OrderLineRepository orderLineRepository;

    //noi trebuie sa obtinem toate orderlineurile
    //orderline ul e in functie de user si order
    @GetMapping
    public ResponseEntity<List<OrderLineResponse>> getOrdersLine(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        return null;

    }
}
