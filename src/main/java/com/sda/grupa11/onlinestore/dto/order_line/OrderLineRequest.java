package com.sda.grupa11.onlinestore.dto.order_line;

import com.sda.grupa11.onlinestore.model.Orders;
import com.sda.grupa11.onlinestore.model.Product;

import java.math.BigDecimal;

public class OrderLineRequest {

    private Product product;
    private int quantity;
    private BigDecimal price;
    private Orders orders;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
