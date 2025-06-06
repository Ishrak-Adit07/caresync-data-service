package com.caresync.service.data.services.implementations;

import com.caresync.service.data.clients.LocationClient;
import com.caresync.service.data.dtos.data.Location;
import com.caresync.service.data.dtos.request.HospitalRegistrationRequest;
import com.caresync.service.data.dtos.request.HospitalUpdateRequest;
import com.caresync.service.data.dtos.request.LocationRequest;
import com.caresync.service.data.dtos.response.HospitalResponse;
import com.caresync.service.data.dtos.response.LocationResponse;
import com.caresync.service.data.entities.Hospital;
import com.caresync.service.data.enums.HOSPITAL_TYPE;
import com.caresync.service.data.repositories.HospitalRepository;
import com.caresync.service.data.services.abstractions.HospitalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final LocationClient locationClient;

    @Override
    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(hospital -> mapToResponse(hospital, null))
                .collect(Collectors.toList());
    }

    @Override
    public HospitalResponse getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + id));

        return mapToResponse(hospital, null);
    }

    @Override
    public List<HospitalResponse> getHospitalsByType(HOSPITAL_TYPE type) {
        return hospitalRepository.findByType(type).stream()
                .map(hospital -> mapToResponse(hospital, null))
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalResponse> getHospitalsByZoneId(Long zoneId) {
        return hospitalRepository.findAll().stream()
                .map(hospital -> mapToResponse(hospital, null))
                .filter(response -> response.locationResponse() != null &&
                        zoneId.equals(response.locationResponse().zoneId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HospitalResponse registerHospital(HospitalRegistrationRequest hospitalRegistrationRequest) {
        if (hospitalRepository.existsByName(hospitalRegistrationRequest.name())) {
            throw new DataIntegrityViolationException("Hospital already exists with Name: " + hospitalRegistrationRequest.name());
        }

        try {
            Location location = hospitalRegistrationRequest.location();

            LocationRequest locationRequest = LocationRequest.builder()
                    .id(location.getId()) // optional
                    .locationType(location.getLocationType())
                    .address(location.getAddress())       // optional
                    .thana(location.getThana())           // optional
                    .po(location.getPo())                 // optional
                    .city(location.getCity())
                    .postalCode(location.getPostalCode())
                    .zoneId(location.getZoneId())
                    .build();

            LocationResponse locationResponse = locationClient.createLocation(locationRequest);

            Hospital newHospital = Hospital.builder()
                    .name(hospitalRegistrationRequest.name())
                    .phoneNumber(hospitalRegistrationRequest.phoneNumber())
                    .website(hospitalRegistrationRequest.website())
                    .locationId(locationResponse.id())
                    .types(hospitalRegistrationRequest.types())
                    .icus(hospitalRegistrationRequest.icus())
                    .build();

            hospitalRepository.save(newHospital);
            return mapToResponse(newHospital, locationResponse);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Failed to register hospital: " + e.getMessage());
        }
    }

    @Override
    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new RuntimeException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HospitalResponse updateHospital(HospitalUpdateRequest hospitalUpdateRequest) {
        Hospital existing = hospitalRepository.findById(hospitalUpdateRequest.id())
                .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + hospitalUpdateRequest.id()));

        if (hospitalUpdateRequest.name() != null) {
            existing.setName(hospitalUpdateRequest.name());
        }

        if (hospitalUpdateRequest.phoneNumber() != null) {
            existing.setPhoneNumber(hospitalUpdateRequest.phoneNumber());
        }

        if (hospitalUpdateRequest.website() != null) {
            existing.setWebsite(hospitalUpdateRequest.website());
        }

        if (hospitalUpdateRequest.types() != null) {
            existing.setTypes(hospitalUpdateRequest.types());
        }

        if (hospitalUpdateRequest.icus() != null) {
            existing.setIcus(hospitalUpdateRequest.icus());
        }

        if (hospitalUpdateRequest.location() != null) {
            LocationRequest locationRequest = LocationRequest.builder()
                    .id(existing.getLocationId()) // existing ID to update
                    .locationType(hospitalUpdateRequest.location().getLocationType())
                    .address(hospitalUpdateRequest.location().getAddress())
                    .thana(hospitalUpdateRequest.location().getThana())
                    .po(hospitalUpdateRequest.location().getPo())
                    .city(hospitalUpdateRequest.location().getCity())
                    .postalCode(hospitalUpdateRequest.location().getPostalCode())
                    .zoneId(hospitalUpdateRequest.location().getZoneId())
                    .build();

            LocationResponse updatedLocation = locationClient.updateLocation(locationRequest);
            existing.setLocationId(updatedLocation.id());
        }

        hospitalRepository.save(existing);
        return mapToResponse(existing, null);
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
