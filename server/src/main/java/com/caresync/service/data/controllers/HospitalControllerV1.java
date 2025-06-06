package com.caresync.service.data.controllers;

import com.caresync.service.data.dtos.request.HospitalRegistrationRequest;
import com.caresync.service.data.dtos.response.HospitalResponse;
import com.caresync.service.data.services.abstractions.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital/v1")
@RequiredArgsConstructor
public class HospitalControllerV1 {

    private final HospitalService hospitalService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hospital service running successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<HospitalResponse>> getAllHospitals() {
        List<HospitalResponse> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HospitalResponse> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<HospitalResponse>> getHospitalsByZone(@PathVariable Long zoneId) {
        List<HospitalResponse> hospitals = hospitalService.getHospitalsByZoneId(zoneId);
        return ResponseEntity.ok(hospitals);
    }

    @PostMapping("/register")
    public ResponseEntity<HospitalResponse> register(@Valid @RequestBody HospitalRegistrationRequest hospitalRegistrationRequest){
        HospitalResponse newHospitalResponse = hospitalService.registerHospital(hospitalRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHospitalResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }


}
