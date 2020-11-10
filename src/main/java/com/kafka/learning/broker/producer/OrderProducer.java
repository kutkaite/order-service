package com.kafka.learning.broker.producer;

import com.kafka.learning.broker.message.OrderMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProducer {
    private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
        int promotionBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;

        List<Header> headers = new ArrayList<>();
        Header promotionBonusHeader = new RecordHeader("promotion", Integer.toString(promotionBonus).getBytes());
        headers.add(promotionBonusHeader);

        return new ProducerRecord<>("t.commodity.order", null, message.getOrderNumber(), message, headers);
    }

    public void publish(OrderMessage message) {
        ProducerRecord<String, OrderMessage> producerRecord = buildProducerRecord(message);

        kafkaTemplate.send(producerRecord).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                LOG.error("Failed to publish order {}, item {}: {}", message.getOrderNumber(), message.getItemName(), ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, OrderMessage> result) {
                LOG.info("Published successfully: order {}, item {}", message.getOrderNumber(), message.getItemName());
            }
        });
    }
}
