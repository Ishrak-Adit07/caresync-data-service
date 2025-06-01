package com.caresync.service.data.dtos.response;

import lombok.Builder;

@Builder
public record LocationResponse(
        String address,
        String thana,
        String po,
        String city,
        String postalCode
) {}
