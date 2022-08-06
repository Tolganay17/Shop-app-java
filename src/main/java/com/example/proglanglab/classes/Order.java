package com.example.proglanglab.classes;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="orderr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderId;

    @OneToMany(mappedBy = "BigOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product>products= new ArrayList();


    private LocalDate order_date;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private User customer;

    public Order(User customer) {
        this.customer = customer;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public Order(List<Product> products, LocalDate order_date) {
        this.products = products;
        this.order_date = order_date;
    }

    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }



    public Order() {
    }

    public Order(LocalDate order_date) {
        this.order_date = order_date;
    }



    public int getOrderId() {
        return OrderId;
    }

    public Order(int orderId, LocalDate order_date) {
        OrderId = orderId;
        this.order_date = order_date;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    @Override
    public String toString() {
        return "com.example.proglanglab.classes.Order{" +
                "OrderId='" + OrderId + '\'' +
               ", order_date=" + order_date +
                '}';
    }
}
