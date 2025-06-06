package com.caresync.service.data.services.implementations;

import com.caresync.service.data.clients.LocationClient;
import com.caresync.service.data.dtos.request.HospitalRegistrationRequest;
import com.caresync.service.data.dtos.response.HospitalResponse;
import com.caresync.service.data.dtos.response.LocationResponse;
import com.caresync.service.data.entities.Hospital;
import com.caresync.service.data.repositories.HospitalRepository;
import com.caresync.service.data.services.abstractions.HospitalService;
import jakarta.transaction.Transactional;
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

//    @Transactional
//    public HospitalResponse registerHospital(HospitalRegistrationRequest hospitalRegistrationRequest) {
//        if (hospitalRepository.existsById(registrationRequest.userId())) {
//            throw new DataIntegrityViolationException("User already exists with ID: " + registrationRequest.userId());
//        }
//
//        try {
//            Location location = registrationRequest.location();
//
//            LocationRequest locationRequest = LocationRequest.builder()
//                    .id(location.getId()) // optional
//                    .locationType(location.getLocationType())
//                    .address(location.getAddress())       // optional
//                    .thana(location.getThana())           // optional
//                    .po(location.getPo())                 // optional
//                    .city(location.getCity())
//                    .postalCode(location.getPostalCode())
//                    .zoneId(location.getZoneId())
//                    .build();
//
//            LocationResponse locationResponse = locationClient.createLocation(locationRequest);
//
//            User newUser = User.builder()
//                    .id(registrationRequest.userId())
//                    .name(registrationRequest.name())
//                    .email(registrationRequest.email())
//                    .passwordHash(registrationRequest.password())
//                    .locationId(locationResponse.id())
//                    .build();
//
//            userRepository.save(newUser);
//            return mapToResponse(newUser, locationResponse);
//
//        } catch (DataIntegrityViolationException e) {
//            userRepository.deleteById(registrationRequest.userId());
//            throw new DataIntegrityViolationException("Failed to register user: " + e.getMessage());
//        }
//    }

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
