package com.gini.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PartResponse {

    private UUID id;
    private int quantity;
    private String partName;
    private BigDecimal price;
    private long partTotal;
    private CarResponse car;
}
