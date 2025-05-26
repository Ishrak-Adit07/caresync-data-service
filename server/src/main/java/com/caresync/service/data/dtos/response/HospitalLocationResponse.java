package com.caresync.service.data.dtos.response;

import lombok.Builder;

@Builder
public record HospitalLocationResponse(
        String address,
        String thana,
        String po,
        String city,
        String postalCode
) {}
