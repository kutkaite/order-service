package com.kafka.learning.command.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.api.request.OrderItemRequest;
import com.kafka.learning.api.request.OrderRequest;
import com.kafka.learning.broker.message.OrderMessage;
import com.kafka.learning.broker.producer.OrderProducer;
import com.kafka.learning.entity.Order;
import com.kafka.learning.entity.OrderItem;
import com.kafka.learning.repository.OrderItemRepository;
import com.kafka.learning.repository.OrderRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAction {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order convertToOrder(OrderRequest request) throws JsonProcessingException {
        Order order = new Order();

        order.setCreditCardNumber(request.getCreditCardNumber());
        order.setOrderLocation(request.getOrderLocation());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

        List<OrderItem> items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
        items.forEach(item -> item.setOrder(order));
        order.setItems(items);

        return order;
    }

    private OrderItem convertToOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();

        orderItem.setItemName(orderItemRequest.getItemName());
        orderItem.setPrice(orderItemRequest.getPrice());
        orderItem.setQuantity(orderItemRequest.getQuantity());

        return orderItem;
    }

    public void saveToDatabase(Order order) {
        orderRepository.save(order);
        order.getItems().forEach(orderItemRepository::save);
    }

    public void publishToKafka(OrderItem orderItem) {
        OrderMessage orderMessage = new OrderMessage();

        orderMessage.setItemName(orderItem.getItemName());
        orderMessage.setPrice(orderItem.getPrice());
        orderMessage.setQuantity(orderItem.getQuantity());

        orderMessage.setCreditCardNumber(orderItem.getOrder().getCreditCardNumber());
        orderMessage.setOrderLocation(orderItem.getOrder().getOrderLocation());
        orderMessage.setOrderDateTime(orderItem.getOrder().getOrderDate());
        orderMessage.setOrderNumber(orderItem.getOrder().getOrderNumber());

        orderProducer.publish(orderMessage);
    }
}
