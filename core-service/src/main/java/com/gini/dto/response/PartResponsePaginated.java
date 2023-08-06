package com.gini.dto.response;

import java.util.List;

public record PartResponsePaginated(
        List<PartResponse> partResponses,
        long nrOfParts

) {
}
