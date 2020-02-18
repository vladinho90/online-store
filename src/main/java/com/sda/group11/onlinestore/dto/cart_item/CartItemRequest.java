package com.sda.group11.onlinestore.dto.cart_item;

import com.sda.group11.onlinestore.model.Product;

public class CartItemRequest {

    private Product product;
    private int quantity;

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
}

