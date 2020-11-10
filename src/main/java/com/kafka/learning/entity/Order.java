package com.kafka.learning.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int orderId;

    @Column(nullable = false, length = 20)
    private String orderNumber;

    @Column(nullable = false, length = 30)
    private String creditCardNumber;

    @Column(nullable = false)
    private String orderLocation;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public int getOrderId() {
        return orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderLocation() {
        return orderLocation;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", orderLocation='" + orderLocation + '\'' +
                ", orderDate=" + orderDate +
                ", items=" + items +
                '}';
    }
}
