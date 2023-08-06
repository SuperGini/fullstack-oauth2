package com.example.apiservice.dto.response;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String manufacturer,
        String carModel
) {
}
