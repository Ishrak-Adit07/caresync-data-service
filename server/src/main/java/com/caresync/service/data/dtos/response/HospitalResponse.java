package com.caresync.service.data.dtos.response;

import com.caresync.service.data.enums.HOSPITAL_TYPE;
import lombok.Builder;

import java.util.List;

@Builder
public record HospitalResponse(
        String id,
        String name,
        String phoneNumber,
        String website,
        List<HOSPITAL_TYPE> types,
        Short icus,
        LocationResponse location
) {}
