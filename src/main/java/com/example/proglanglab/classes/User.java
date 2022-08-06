package com.example.proglanglab.classes;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> myOrders;
    private boolean hasRights;

    public boolean isHasRights() {
        return hasRights;
    }

    public User(String username, String password, List<Order> myOrders, boolean hasRights) {
        this.username = username;
        this.password = password;
        this.myOrders = myOrders;
        this.hasRights = hasRights;
    }


    public void setHasRights(boolean hasRights) {
        this.hasRights = hasRights;
    }
    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void remove(User user);
}
