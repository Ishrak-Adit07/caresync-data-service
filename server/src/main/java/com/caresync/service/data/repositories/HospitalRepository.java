package com.caresync.service.data.repositories;

import com.caresync.service.data.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByName(String name);
    Boolean existsByName(String name);

}
