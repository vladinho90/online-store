package com.sda.group11.onlinestore.controller;


import com.sda.group11.onlinestore.dto.order_line.OrderLineMapper;
import com.sda.group11.onlinestore.dto.orders.OrderMapper;
import com.sda.group11.onlinestore.dto.orders.OrderResponse;
import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.OrderLine;
import com.sda.group11.onlinestore.repository.OrderLineRepository;
import com.sda.group11.onlinestore.service.IOrderLineService;
import com.sda.group11.onlinestore.service.IUserService;
import com.sda.group11.onlinestore.dto.order_line.OrderLineResponse;
import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.service.impl.CartServiceImpl;
import com.sda.group11.onlinestore.service.impl.OrderLineServiceImpl;
import com.sda.group11.onlinestore.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(CartController.API_CART)
public class CartController {

    public static final String API_CART = "/cart";

    @Autowired
    public IUserService userService;

    @Autowired
    public IOrderLineService orderLineService;

    @Autowired
    public OrderLineMapper orderLineMapper;

    @Autowired
    public OrderLineRepository orderLineRepository;

    @Autowired
    public CartServiceImpl cartService;

    @Autowired
    public OrderMapper orderMapper;


    //noi trebuie sa obtinem toate orderlineurile
    //orderline ul e in functie de user si order
    @GetMapping()
    public ResponseEntity<List<OrderLineResponse>> getOrdersLine(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        return null;

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getCart(@PathVariable (name = "id") Long cartId){
        Order order = cartService.checkout(cartId);

        OrderResponse orderResponses = orderMapper.toDto(order);
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }
}
