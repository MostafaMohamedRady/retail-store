package com.billing.controller;

import com.billing.service.BillService;
import com.billing.dto.BillRequest;
import com.billing.dto.BillResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/bill")
@Api("Bill Calculator System")
@Slf4j
public class BillController {

    @Autowired
    private BillService billService;

    @ApiOperation(value = "return net amount of bill after discount",response = BillResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved bill amount"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error. Please contact admin.")
    })
    @PostMapping("/discount")
    public ResponseEntity<BillResponse> calculateBill(@Valid @NotNull @RequestBody BillRequest request){
        log.info("Customer {} :- bill request received {}", request.getCustomerDetails().getName(), request);
        return ResponseEntity.ok(billService.calculateNetPayable(request));
    }
}
