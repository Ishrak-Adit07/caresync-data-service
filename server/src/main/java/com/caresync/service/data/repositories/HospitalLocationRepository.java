package com.caresync.service.data.repositories;

import com.caresync.service.data.entities.HospitalLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalLocationRepository extends JpaRepository<HospitalLocation, Long> {
}
