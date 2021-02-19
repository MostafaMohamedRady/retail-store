package com.billing.billingdiscount;

import com.billing.dto.CustomerDetails;
import com.billing.dto.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataInitializer {


    /**
     * not apply discount
     */
    public static Item getGroceryItem(int quantity) {
        Item item = new Item("Apple", 100.00, true, quantity);
        return item;
    }

    /**
     * apply discount
     */
    public static Item getNonGroceryItem(int quantity) {
        Item item = new Item("Mobile", 150.00, false, quantity);
        return item;
    }

    public static CustomerDetails getAffiliateCustomer() {
        CustomerDetails customerDetails = new CustomerDetails("customer-101", new Date(), false, true);
        return customerDetails;
    }

    public static CustomerDetails getEmployeeCustomer() {
        CustomerDetails customerDetails = new CustomerDetails("customer-102", new Date(), true, false);
        return customerDetails;
    }

    public static CustomerDetails getLoyaltyCustomer() {
        CustomerDetails customerDetails = new CustomerDetails("customer-103", new Date(1442952000000L), false, false);
        return customerDetails;
    }

    public static CustomerDetails getNonLoyaltyCustomer() {
        CustomerDetails customerDetails = new CustomerDetails("customer-104", new Date(), false, false);
        return customerDetails;
    }

    public static List<Item> getMixedItems() {
        List<Item> items = new ArrayList<>();
        items.add(getGroceryItem(4));
        items.add(getNonGroceryItem(4));
        return items;
    }
}
