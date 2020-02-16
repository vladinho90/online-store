package com.sda.group11.onlinestore.dto.order_line;

import com.sda.group11.onlinestore.dto.orders.OrderResponse;
import com.sda.group11.onlinestore.dto.product.ProductResponse;
import com.sda.group11.onlinestore.model.Order;
import com.sda.group11.onlinestore.model.Product;

import java.math.BigDecimal;

public class OrderLineResponse {

    private Long id;
    private ProductResponse productResponse;
    private int quantity;
    private BigDecimal price;
    private OrderResponse orderResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductResponse getProductResponse() {
        return productResponse;
    }

    public void setProductResponse(ProductResponse productResponse) {
        this.productResponse = productResponse;
    }

    public OrderResponse getOrderResponse() {
        return orderResponse;
    }

    public void setOrderResponse(OrderResponse orderResponse) {
        this.orderResponse = orderResponse;
    }
}
