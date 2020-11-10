package com.kafka.learning.command.action;

import com.kafka.learning.api.request.PromotionRequest;
import com.kafka.learning.broker.message.PromotionMessage;
import com.kafka.learning.broker.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromotionAction {

    @Autowired
    PromotionProducer promotionProducer;

    public void publishToKafka(PromotionRequest promotionRequest) {
        PromotionMessage promotionMessage = new PromotionMessage();
        promotionMessage.setPromotionCode(promotionRequest.getPromotionCode());

        promotionProducer.publish(promotionMessage);
    }
}
