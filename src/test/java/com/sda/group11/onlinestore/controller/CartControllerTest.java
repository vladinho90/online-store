package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.RestIntegrationTest;
import com.sda.group11.onlinestore.dto.order_line.OrderLineResponse;
import com.sda.group11.onlinestore.dto.orders.OrderResponse;
import com.sda.group11.onlinestore.model.*;
import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.repository.CartItemRepository;
import com.sda.group11.onlinestore.repository.CartRepository;
import com.sda.group11.onlinestore.repository.ProductRepository;
import com.sda.group11.onlinestore.repository.UserRepository;
import com.sda.group11.onlinestore.service.impl.CartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CartControllerTest extends RestIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Test
//    @Transactional
    void givenExistingCart_whenCheckout_thenReturnOrderLineList() {
        //given
        Address address = createAddress();
        Product product = createProduct1();

        User user = new User();
        user.setUsername("vlad");
        user.setAddress(address);
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setCart(new Cart());

        user = userRepository.save(user);


        Cart expectedCart = cartRepository.findById(user.getCart().getId()).get();
        CartItem cartItem = createCartItem(product, expectedCart);

        Product product2 = createProduct2();
        CartItem cartItem2 = createCartItem2(product2, expectedCart);
        cartItem2.setQuantity(2);
        Set<CartItem> set = new HashSet<>();
        set.add(cartItem);
        set.add(cartItem2);
        expectedCart.setCartItemSet(set);


        cartRepository.save(expectedCart);

        //when
        String relativePath = "/cart" + "/" + expectedCart.getId();
        ResponseEntity<OrderResponse> response = this.restTemplate
                .getForEntity(url(relativePath), OrderResponse.class);

        Cart cart = cartRepository.findById(expectedCart.getId()).orElseGet(Assertions::fail);

        BigDecimal totalPrice = getTotalPrice(set);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getOrderLineResponsesList()).hasSize(2);
        assertThat(cart.getCartItemSet()).isEmpty();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertThat(response.getBody().getTotalPrice().compareTo(totalPrice.setScale(2)));
    }

    private BigDecimal getTotalPrice (Set<CartItem> cartItems){
       return cartItems.stream()
                .map(this::getPricePerCartItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getPricePerCartItem (CartItem cartItem){
        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return totalPrice;
    }

    private CartItem createCartItem(Product product, Cart expectedCart) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setCart(expectedCart);
        cartItem.setProduct(product);
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    private CartItem createCartItem2(Product product2, Cart expectedCart) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setCart(expectedCart);
        cartItem.setProduct(product2);
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    private Product createProduct1() {
        Product product = new Product();
        product.setUnitsInStock(5);
        product.setStock(true);
        product.setPrice(BigDecimal.valueOf(10));
        product.setPictureURL("/url.jpeg");
        product.setCategory(Category.SHOES);
        product.setTitle("AIr max");
        productRepository.save(product);
        return product;
    }

    private Product createProduct2() {
        Product product = new Product();
        product.setUnitsInStock(3);
        product.setStock(true);
        product.setPrice(BigDecimal.valueOf(20));
        product.setPictureURL("/url2.jpeg");
        product.setCategory(Category.ACCESSORIES);
        product.setTitle("cercei");
        productRepository.save(product);
        return product;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCity("Braila");
        address.setCountry("Romania");
        address.setPhoneNumber("0721639537");
        address.setStreet("Plantelor nr.34");
        address.setZipCOde(810137);
        return address;
    }
}
