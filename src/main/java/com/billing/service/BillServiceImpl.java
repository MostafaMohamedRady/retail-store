package com.billing.service;

import com.billing.constant.AppConstant;
import com.billing.constant.CustomerType;
import com.billing.dto.BillRequest;
import com.billing.dto.BillResponse;
import com.billing.dto.CustomerDetails;
import com.billing.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    /**
     * calculate the net for the given bill
     *
     * @param bill
     * @return
     */
    @Override
    public BillResponse calculateNetPayable(BillRequest bill) {
        log.info("Bill calculation service started");

        double netAmount = 0.0;
        double totalAmount = 0.0;
        int percentageDiscount = getDiscountPerCustomerType(bill.getCustomerDetails());
        for (Item item : bill.getItems()) {
            netAmount += applyPercentageDiscount(item, percentageDiscount);
            totalAmount += item.getTotalCost();
        }
        double discountPerAmount = applyTotalBillDiscount(netAmount);
        netAmount = Math.round(netAmount - discountPerAmount);
        return new BillResponse(bill.getCustomerDetails(), totalAmount, percentageDiscount, discountPerAmount, netAmount);
    }


    /**
     * get discount percentage according to customer type
     * If the user is an employee of the store, he gets a 30% discount
     * If the user is an affiliate of the store, he gets a 10% discount
     * If the user has been a customer for over 2 years, he gets a 5% discount.
     */
    private int getDiscountPerCustomerType(CustomerDetails customerDetails) {
        if (customerDetails.isEmployee()) {
            return CustomerType.EMPLOYEE.getDiscountPercentage();
        } else if (customerDetails.isAffiliate()) {
            return CustomerType.AFFILIATE.getDiscountPercentage();
        } else if (customerDetails.isLoyalClient()) {
            return CustomerType.LOYALTY.getDiscountPercentage();
        } else {
            log.info("customer {} :- is not applicable for any discount percentage", customerDetails.getName());
            return 0;
        }
    }

    /**
     * calculate discount per total amount
     * For every $100 on the bill, there would be a $ 5 discount
     */
    private double applyTotalBillDiscount(double totalDiscountedPrice) {
        return Math.floor(totalDiscountedPrice / 100) * AppConstant.TOTAL_BILL_DISCOUNT;
    }

    /**
     * calculate price of items after discount percentage
     *
     * @param item
     * @param discountPercentage
     * @return
     */
    private double applyPercentageDiscount(Item item, double discountPercentage) {
        if (!item.isGrocery() && discountPercentage != 0) {
            return (item.getTotalCost() - item.getTotalCost() * discountPercentage / 100);
        }
        return item.getTotalCost();
    }
}
