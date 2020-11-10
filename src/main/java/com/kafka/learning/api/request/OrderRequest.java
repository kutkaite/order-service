package com.kafka.learning.api.request;

import java.util.List;

public class OrderRequest {

    private String orderLocation;
    private String creditCardNumber;
    private List<OrderItemRequest> items;

    public String getOrderLocation() {
        return orderLocation;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderLocation='" + orderLocation + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", items=" + items +
                '}';
    }
}
