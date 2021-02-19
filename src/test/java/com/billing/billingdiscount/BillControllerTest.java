package com.billing.billingdiscount;

import com.billing.controller.BillController;
import com.billing.dto.BillRequest;
import com.billing.dto.BillResponse;
import com.billing.dto.CustomerDetails;
import com.billing.dto.Item;
import com.billing.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BillControllerTest {

    @InjectMocks
    private BillController controller;
    @Mock
    private BillService billService;

    private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testCalculateBill() {
        BillResponse billResponse = new BillResponse(DataInitializer.getEmployeeCustomer(), 200, 10, 5, 175);
        Mockito.when(billService.calculateNetPayable(Mockito.isA(BillRequest.class))).thenReturn(billResponse);
        BillRequest billRequest = new BillRequest();
        billRequest.setCustomerDetails(DataInitializer.getEmployeeCustomer());
        billRequest.setItems(DataInitializer.getMixedItems());
        ResponseEntity<BillResponse> responseEntity = controller.calculateBill(billRequest);
        assertNotNull(responseEntity);
    }

    @Test
    public void testRestWorking() throws Exception {
        CustomerDetails customerDetails = DataInitializer.getLoyaltyCustomer();
        List<Item> items = DataInitializer.getMixedItems();
        BillRequest billRequest = new BillRequest(customerDetails, items);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/bill/discount")
                .contentType(MediaType.APPLICATION_JSON).content(mapToJson(billRequest)))
                .andExpect(status().isOk()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String billResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("--------------->>>>>>>>>  " + billResponse);

    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
