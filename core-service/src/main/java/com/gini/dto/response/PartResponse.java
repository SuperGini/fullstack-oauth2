package com.gini.dto.response;


import com.gini.model.Car;

import java.math.BigDecimal;
import java.util.UUID;

public record PartResponse(

        UUID id,
        String partName,
        BigDecimal price,
        Car car
) {
}
