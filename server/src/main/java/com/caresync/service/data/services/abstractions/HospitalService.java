package com.caresync.service.data.services.abstractions;

import com.caresync.service.data.dtos.request.HospitalRegistrationRequest;
import com.caresync.service.data.dtos.request.HospitalUpdateRequest;
import com.caresync.service.data.dtos.response.HospitalResponse;
import com.caresync.service.data.enums.HOSPITAL_TYPE;

import java.util.List;

public interface HospitalService {

    List<HospitalResponse> getAllHospitals();
    HospitalResponse getHospitalById(Long id);
    List<HospitalResponse> getHospitalsByType(HOSPITAL_TYPE type);
    List<HospitalResponse> getHospitalsByZoneId(Long zoneId);
    void deleteHospital(Long id);
    HospitalResponse updateHospital(HospitalUpdateRequest hospitalUpdateRequest);
    HospitalResponse registerHospital(HospitalRegistrationRequest hospitalRegistrationRequest);

}
