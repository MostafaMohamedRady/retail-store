package com.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {

    private CustomerDetails customerDetails;
    private double totalAmount;
    private String percentageDiscount;
    private double discountPerAmount;
    private double totalDiscount;
    private double netAmount;

    public BillResponse(CustomerDetails customerDetails, double totalAmount, int percentageDiscount, double discountPerAmount, double netAmount) {
        this.customerDetails = customerDetails;
        this.totalAmount = totalAmount;
        this.percentageDiscount = percentageDiscount + "%";
        this.discountPerAmount = discountPerAmount;
        this.totalDiscount = totalAmount - netAmount;
        this.netAmount = netAmount;
    }
}
