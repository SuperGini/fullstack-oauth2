package com.gini.dto.request;

import java.math.BigDecimal;

public record PartRequest(

        String partName,
        BigDecimal price,
        CarRequest carRequest
) {
}
