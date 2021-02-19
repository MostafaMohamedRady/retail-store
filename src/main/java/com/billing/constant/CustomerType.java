package com.billing.constant;

public enum CustomerType {

    AFFILIATE(10),EMPLOYEE(30), LOYALTY(5);

    private int discountPercentage;

    CustomerType(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

}
