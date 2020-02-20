package com.sda.group11.onlinestore.service.impl;


import com.sda.group11.onlinestore.model.*;
import com.sda.group11.onlinestore.model.enums.Status;
import com.sda.group11.onlinestore.repository.CartItemRepository;
import com.sda.group11.onlinestore.repository.CartRepository;
import com.sda.group11.onlinestore.repository.OrderRepository;
import com.sda.group11.onlinestore.service.ICartService;
import com.sda.group11.onlinestore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    public IOrderService orderService;

    @Autowired
    public CartServiceImpl cartService;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public ProductServiceImpl productService;

    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    public void delete(Long cartItemId, User user) {
        Optional<CartItem> cartItem = getCart(user).getCartItemSet()
                .stream().filter(item -> cartItemId.equals(item.getProduct().getId())).findFirst();
        cartItem.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            cartRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }


    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("cart with id " + id + " not found"));
    }

    @Override
    public void addToCart(CartItem cartItem, User user) {
        Cart finalCart = user.getCart();
        Set<CartItem> set = finalCart.getCartItemSet();
        Optional<CartItem> old = set.stream().filter(item -> item.getProduct().getId().equals(cartItem.getProduct().getId())).findFirst();
        CartItem item;
        if (old.isPresent()) {
            item = old.get();
            item.setQuantity(cartItem.getQuantity() + item.getQuantity());
        } else {
            item = cartItem;
            item.setCart(finalCart);
            finalCart.getCartItemSet().add(item);
        }
        cartItemRepository.save(item);

        cartRepository.save(finalCart);
    }

    @Override
    public void deleteCart(Long cartItemId, User user) {
        Optional<CartItem> cartItemOptional = user.getCart().getCartItemSet().stream().filter(itemId -> cartItemId.equals(itemId.getId())).findFirst();
        cartItemOptional.ifPresent(cartItem -> {
            cartItem.setCart(null);
            cartItemRepository.deleteById(cartItem.getId());
        });
    }

    @Override
    public Order checkout(Long cartId) {
        Order order = new Order();
        order.setStatus(Status.NEW);

        Cart cart = findCartById(cartId);

        List<OrderLine> orderLineList = cart.getCartItemSet().stream()
                .map(cartItem -> {
                    OrderLine orderLine = new OrderLine();
                    orderLine.setProduct(cartItem.getProduct());
                    orderLine.setOrder(order);
                    orderLine.setPrice(getPricePerOrderLine(cartItem));
                    orderLine.setQuantity(cartItem.getQuantity());

                    return orderLine;
                })
                .collect(Collectors.toList());

        //trebuie sa facem o metoda care sa scada unitatile din stoc
        orderLineList.forEach(orderLine ->
                productService.decreaseStock(orderLine.getProduct().getId(), orderLine.getQuantity()));

        BigDecimal totalPrice = orderLineList.stream()
                .map(OrderLine::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);

        order.setOrderLineList(orderLineList);
        orderRepository.save(order);

        cart.deleteAllItems();

        return order;
    }


    @Override
    public BigDecimal getPricePerOrderLine(CartItem cartItem) {
        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return totalPrice;
    }
}
