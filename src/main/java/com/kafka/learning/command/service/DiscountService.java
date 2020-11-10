package com.kafka.learning.command.service;

import com.kafka.learning.api.request.DiscountRequest;
import com.kafka.learning.command.action.DiscountAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    private DiscountAction discountAction;

    public String createDiscount(DiscountRequest request) {
        discountAction.publishToKafka(request);

        return request.getDiscountCode();
    }
}
