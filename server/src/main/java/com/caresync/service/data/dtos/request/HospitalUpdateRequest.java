package com.caresync.service.data.dtos.request;

import com.caresync.service.data.dtos.data.Location;
import com.caresync.service.data.enums.HOSPITAL_TYPE;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HospitalUpdateRequest (
        Long id,

        @Size(max = 150)
        String name,

        @Size(max = 20)
        String phoneNumber,

        @Size(max = 255)
        String website,

        List<HOSPITAL_TYPE> types,

        Location location,

        @Min(0)
        Short icus
){}
