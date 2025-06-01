package com.caresync.service.data.services.implementations;

import com.caresync.service.data.dtos.response.HospitalResponse;
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

    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HospitalResponse mapToResponse(Hospital hospital) {

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .phoneNumber(hospital.getPhoneNumber())
                .website(hospital.getWebsite())
                .types(hospital.getTypes())
                .icus(hospital.getIcus())
                .location(null)
                .build();
    }
}
