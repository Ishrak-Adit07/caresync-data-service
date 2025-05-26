package com.caresync.service.data.services.abstractions;

import com.caresync.service.data.dtos.response.HospitalResponse;

import java.util.List;

public interface HospitalService {

    List<HospitalResponse> getAllHospitals();

}
