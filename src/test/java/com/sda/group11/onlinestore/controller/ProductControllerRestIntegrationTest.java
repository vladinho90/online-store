package com.sda.group11.onlinestore.controller;

import com.sda.group11.onlinestore.RestIntegrationTest;
import com.sda.group11.onlinestore.model.Product;
import com.sda.group11.onlinestore.model.enums.Category;
import com.sda.group11.onlinestore.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductControllerRestIntegrationTest extends RestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void givenExistingProduct_whenFindProductById_thenReturnProduct() {
        //given
        Product expectedProduct = new Product();
        expectedProduct.setTitle("Nike AF1 Shadow");
        expectedProduct.setCategory(Category.SHOES);
        expectedProduct.setPictureURL("client-server.jpeg");
        expectedProduct.setPrice(BigDecimal.valueOf(530));
        expectedProduct.setStock(true);
        expectedProduct.setUnitsInStock(1);

        expectedProduct = productRepository.save(expectedProduct);

        //when
        String relativePath = "/product" + "/" + expectedProduct.getId();
        ResponseEntity<Product> response = this.restTemplate
                .getForEntity(url(relativePath), Product.class);

        //then
        assertEquals(expectedProduct, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}