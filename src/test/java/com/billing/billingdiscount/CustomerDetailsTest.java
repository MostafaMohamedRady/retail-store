package com.billing.billingdiscount;

import com.billing.dto.CustomerDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDetailsTest {

    @Test
    public void testLoyaltyClient() {
        CustomerDetails customerDetails = DataInitializer.getLoyaltyCustomer();
        Assert.assertTrue(customerDetails.isLoyalClient());
    }

    @Test
    public void testNonLoyaltyClient() {
        CustomerDetails customerDetails = DataInitializer.getNonLoyaltyCustomer();
        Assert.assertFalse(customerDetails.isLoyalClient());
    }

    @Test
    public void testClientType() {
        CustomerDetails customerDetails = DataInitializer.getEmployeeCustomer();
        Assert.assertFalse(customerDetails.isLoyalClient());
    }

}
