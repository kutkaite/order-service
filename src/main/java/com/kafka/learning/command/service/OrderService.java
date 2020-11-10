package com.kafka.learning.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.learning.api.request.OrderRequest;
import com.kafka.learning.command.action.OrderAction;
import com.kafka.learning.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderAction orderAction;

    public String saveOrder(OrderRequest request) throws JsonProcessingException {
        Order order = orderAction.convertToOrder(request);
        orderAction.saveToDatabase(order);

        order.getItems().forEach(orderAction::publishToKafka);

        return order.getOrderNumber();
    }
}
