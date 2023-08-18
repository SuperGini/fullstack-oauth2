package com.example.apiservice.dto.request;

import java.math.BigDecimal;

public record PartRequest(

        String partName,
        int quantity,
        BigDecimal price,
        CarRequest carRequest
) {
}
