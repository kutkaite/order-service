package com.kafka.learning.broker.message;

public class OrderReplyMessage {
    private String messageReply;

    public String getMessageReply() {
        return messageReply;
    }

    public void setMessageReply(String messageReply) {
        this.messageReply = messageReply;
    }

    @Override
    public String toString() {
        return "OrderReplyMessage{" +
                "messageReply='" + messageReply + '\'' +
                '}';
    }
}
