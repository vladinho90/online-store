package com.sda.grupa11.onlinestore.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity(name="Orders")
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "delivery_address")
    @NotNull
    @Embedded
    private Address deliveryAddress;

    @Column(name = "total_price")
    //@NotNull
    private BigDecimal totalPrice;

    @Column(name = "status")
    @NotNull
    private String status;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderLine> orderLineList;

    public Orders(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLine) {
        this.orderLineList = orderLine;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user=" + user +
                ", deliveryAddress=" + deliveryAddress +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", orderLine=" + orderLineList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id.equals(orders.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
