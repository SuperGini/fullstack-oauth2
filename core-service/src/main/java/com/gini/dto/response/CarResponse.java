package com.gini.dto.response;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String manufacturer,
        String carModel
) {
}
