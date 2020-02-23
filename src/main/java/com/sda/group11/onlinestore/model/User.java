package com.sda.group11.onlinestore.model;

import com.sda.group11.onlinestore.model.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;


@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="username",unique = true)
    @NotNull
    private String username;

    @Email(message = "This is not a valid email")
    @Column(name = "email",unique = true)
    @NotNull
    private String email;

    @Column(name="full_name")
   // @NotNull
    private String fullName;

    // @NotNull
    @Embedded
    private Address address;

    //TODO sa facem si enabled?
    @Column(name="role")
    //@NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    //TODO userul cred ca ar trebui sa porneasca cu un cart nou
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> ordersList;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //TODO ma bate putin asta
    public void setCart(Cart cart) {
        cart.setUser(this);
        this.cart = cart;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", role=" + role +
                ", cart=" + cart +
                ", ordersList=" + ordersList +
                '}';
    }
}

