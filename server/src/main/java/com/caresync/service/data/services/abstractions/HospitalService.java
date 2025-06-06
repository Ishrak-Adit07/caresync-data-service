package com.caresync.service.data.services.abstractions;

import com.caresync.service.data.dtos.request.HospitalRegistrationRequest;
import com.caresync.service.data.dtos.response.HospitalResponse;

import java.util.List;

public interface HospitalService {

    List<HospitalResponse> getAllHospitals();
    HospitalResponse getHospitalById(Long id);
    List<HospitalResponse> getHospitalsByZoneId(Long zoneId);
    void deleteHospital(Long id);
    HospitalResponse updateHospital(HospitalRegistrationRequest hospitalRegistrationRequest);
    HospitalResponse registerHospital(HospitalRegistrationRequest hospitalRegistrationRequest);

}
