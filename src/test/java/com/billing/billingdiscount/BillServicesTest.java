package com.billing.billingdiscount;

import com.billing.dto.BillRequest;
import com.billing.dto.BillResponse;
import com.billing.service.BillServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BillServicesTest {

    @InjectMocks
    private BillServiceImpl service;

    @Test
    public void testDiscountForLoyaltyClient() {
        BillRequest billRequest = new BillRequest();
        billRequest.setCustomerDetails(DataInitializer.getLoyaltyCustomer());
        billRequest.setItems(DataInitializer.getMixedItems());
        BillResponse billResponse = service.calculateNetPayable(billRequest);
        Assert.assertNotNull(billResponse);
        Assert.assertEquals(925.00, billResponse.getNetAmount(), 0);
    }

    @Test
    public void testNoDiscountForNonLoyaltyClient() {
        BillRequest billRequest = new BillRequest();
        billRequest.setCustomerDetails(DataInitializer.getNonLoyaltyCustomer());
        billRequest.setItems(DataInitializer.getMixedItems());

        Assert.assertEquals(950.00, service.calculateNetPayable(billRequest).getNetAmount(),0);
    }

    @Test
    public void testDiscountForAffiliateClient() {
        BillRequest billRequest = new BillRequest();
        billRequest.setCustomerDetails(DataInitializer.getAffiliateCustomer());
        billRequest.setItems(DataInitializer.getMixedItems());

        Assert.assertEquals(895.00, service.calculateNetPayable(billRequest).getNetAmount(),0);
    }

    @Test
    public void testDiscountForEmployeeClient() {
        BillRequest billRequest = new BillRequest();
        billRequest.setCustomerDetails(DataInitializer.getEmployeeCustomer());
        billRequest.setItems(DataInitializer.getMixedItems());

        Assert.assertEquals(780.00, service.calculateNetPayable(billRequest).getNetAmount(),0);
    }
}
