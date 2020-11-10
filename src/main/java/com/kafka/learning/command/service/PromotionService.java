package com.kafka.learning.command.service;

import com.kafka.learning.api.request.PromotionRequest;
import com.kafka.learning.command.action.PromotionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    @Autowired
    private PromotionAction promotionAction;

    public String createPromotion(PromotionRequest request) {
        promotionAction.publishToKafka(request);

        return request.getPromotionCode();
    }
}
