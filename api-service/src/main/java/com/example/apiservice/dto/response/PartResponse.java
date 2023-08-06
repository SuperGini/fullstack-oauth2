package com.example.apiservice.dto.response;


import java.math.BigDecimal;
import java.util.UUID;

public record PartResponse(
        UUID id,
        String partName,
        BigDecimal price,
        CarResponse car
) {
}
