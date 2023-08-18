package com.gini.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartResponse {

    private UUID id;
    private int quantity;
    private String partName;
    private BigDecimal price;
    private long partTotal;
    private CarResponse car;
}
