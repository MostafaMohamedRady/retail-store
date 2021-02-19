package com.billing.service;

import com.billing.dto.BillRequest;
import com.billing.dto.BillResponse;

public interface BillService {

    BillResponse calculateNetPayable(BillRequest bill);
}
