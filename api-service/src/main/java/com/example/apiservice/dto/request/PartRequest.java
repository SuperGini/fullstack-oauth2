package com.example.apiservice.dto.request;

import java.math.BigDecimal;

public record PartRequest(

        String partName,
        BigDecimal price,
        CarRequest carRequest
) {
}
