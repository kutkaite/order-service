package com.kafka.learning.api.server;

import com.kafka.learning.api.request.DiscountRequest;
import com.kafka.learning.command.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/discount")
public class DiscountApi {
    @Autowired
    private DiscountService discountService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createDiscount(@RequestBody DiscountRequest request) {
        discountService.createDiscount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(request.getDiscountCode());
    }
}
