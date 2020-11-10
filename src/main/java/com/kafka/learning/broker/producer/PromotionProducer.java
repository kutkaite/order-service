package com.kafka.learning.broker.producer;

import com.kafka.learning.broker.message.PromotionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PromotionProducer {
    private static final Logger LOG = LoggerFactory.getLogger(PromotionProducer.class);

    @Autowired
    private KafkaTemplate<String, PromotionMessage> kafkaTemplate;

    public void publish(PromotionMessage message) {
        try {
            SendResult<String, PromotionMessage> sendResult = kafkaTemplate.send("t.commodity.promotion", message)
                    .get(); //synchronous, risk the publisher to be blocked
            LOG.info("Successfully sent message {}", sendResult.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Failed to publish message {} {}", message, e.getMessage());
        }
    }
}
