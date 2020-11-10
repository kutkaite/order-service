package com.kafka.learning.api.request;

public class DiscountRequest {

    private String discountCode;

    private Integer discountPercentage;

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "DiscountRequest{" +
                "discountCode='" + discountCode + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
