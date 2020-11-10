package com.kafka.learning.command.action;

import com.kafka.learning.api.request.DiscountRequest;
import com.kafka.learning.api.request.PromotionRequest;
import com.kafka.learning.broker.message.DiscountMessage;
import com.kafka.learning.broker.message.PromotionMessage;
import com.kafka.learning.broker.producer.DiscountProducer;
import com.kafka.learning.broker.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountAction {

    @Autowired
    DiscountProducer discountProducer;

    public void publishToKafka(DiscountRequest discountRequest) {
        DiscountMessage discountMessage = new DiscountMessage();
        discountMessage.setDiscountCode(discountRequest.getDiscountCode());
        discountMessage.setDiscountPercentage(discountRequest.getDiscountPercentage());

        discountProducer.publish(discountMessage);
    }
}
