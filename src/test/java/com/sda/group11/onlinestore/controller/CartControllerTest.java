package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.RestIntegrationTest;
import com.sda.group11.onlinestore.dto.order_line.OrderLineResponse;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Product product = createProduct();

        User user = new User();
        user.setUsername("vlad");
        user.setAddress(address);
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setCart(new Cart());

        user = userRepository.save(user);


        Cart expectedCart = cartRepository.findById(user.getCart().getId()).get();
        CartItem cartItem = createCartItem(product, expectedCart);
        Set<CartItem> set = new HashSet<>();
        set.add(cartItem);
        expectedCart.setCartItemSet(set);

        cartRepository.save(expectedCart);

        //when
        String relativePath = "/cart" + "/" + expectedCart.getId();
        ResponseEntity<OrderLineResponse[]> response = this.restTemplate
                .getForEntity(url(relativePath), OrderLineResponse[].class);

        Cart cart = cartRepository.findById(expectedCart.getId()).orElseGet(Assertions::fail);
        List<CartItem> cartItemList = cartItemRepository.findAll();
        //then
        assertThat(response.getBody()).hasSize(1);
        assertThat(cartItemList).isEmpty();
        assertThat(cart.getCartItemSet()).isEmpty();
//        assertEquals(expectedCart, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private CartItem createCartItem(Product product, Cart expectedCart) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setCart(expectedCart);
        cartItem.setProduct(product);
        return cartItem;
    }

    private Product createProduct() {
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
