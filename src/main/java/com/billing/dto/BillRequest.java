package com.billing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Bill Request", description = "Request contain client details and item list with price.")
public class BillRequest {
    @ApiModelProperty(value = "customer details", required = true)
    @NotNull(message = "customer details is required in the request")
    private CustomerDetails customerDetails;
    @ApiModelProperty(value = "item list", required = true)
    @NotEmpty(message = "items is required in the request")
    private List<Item> items;
}
