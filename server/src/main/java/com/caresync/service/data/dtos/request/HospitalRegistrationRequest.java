package com.caresync.service.data.dtos.request;

import com.caresync.service.data.dtos.data.Location;
import com.caresync.service.data.enums.HOSPITAL_TYPE;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record HospitalRegistrationRequest (

    @NotBlank(message = "Hospital name cannot be blank")
    @Size(max = 150)
    String name,

    @Size(max = 20)
    String phoneNumber,

    @Size(max = 255)
    String website,

    @NotEmpty(message = "At least one hospital type must be specified")
    List<HOSPITAL_TYPE> types,

    @Valid
    Location location

){}