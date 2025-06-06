package com.caresync.service.data.services.implementations;

import com.caresync.service.data.clients.LocationClient;
import com.caresync.service.data.dtos.response.HospitalResponse;
import com.caresync.service.data.dtos.response.LocationResponse;
import com.caresync.service.data.entities.Hospital;
import com.caresync.service.data.repositories.HospitalRepository;
import com.caresync.service.data.services.abstractions.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final LocationClient locationClient;

    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(hospital -> mapToResponse(hospital, null))
                .collect(Collectors.toList());
    }


    private HospitalResponse mapToResponse(Hospital hospital, LocationResponse locationResponse) {
        if (locationResponse == null && hospital.getLocationId() != null) {
            locationResponse = locationClient.getLocationById(hospital.getLocationId());
        }

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .phoneNumber(hospital.getPhoneNumber())
                .website(hospital.getWebsite())
                .types(hospital.getTypes())
                .icus(hospital.getIcus())
                .locationResponse(locationResponse)
                .build();
    }
}
