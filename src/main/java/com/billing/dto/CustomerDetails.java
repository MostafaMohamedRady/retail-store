package com.billing.dto;

import com.billing.constant.AppConstant;
import com.billing.constant.CustomerType;
import com.billing.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Customer Details", description = "the object contains customer details.")
public class CustomerDetails {

    private String name;
    private Date registrationDate;
    private boolean employee;
    private boolean affiliate;

    /**
     * check if this client available for loyal discount (more than two years)
     *
     * @returnn
     */
    @JsonIgnore
    public boolean isLoyalClient() {
        return CommonUtil.calculatePeriod(registrationDate) >= AppConstant.LOYALTY_YEARS;
    }


}
