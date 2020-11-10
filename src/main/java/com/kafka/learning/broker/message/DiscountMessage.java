package com.kafka.learning.broker.message;

public class DiscountMessage {

    private String discountCode;

    private Integer discountPercentage;

    public DiscountMessage() {
    }

    public DiscountMessage(String discountCode, Integer discountPercentage) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public String toString() {
        return "DiscountMessage{" +
                "discountCode='" + discountCode + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
