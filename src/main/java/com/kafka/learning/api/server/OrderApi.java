package com.kafka.learning.api.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.learning.api.request.OrderRequest;
import com.kafka.learning.api.response.OrderResponse;
import com.kafka.learning.command.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
        String orderNumber = orderService.saveOrder(request);
        OrderResponse orderResponse = new OrderResponse(orderNumber);

        return ResponseEntity.ok().body(orderResponse);
    }
}
