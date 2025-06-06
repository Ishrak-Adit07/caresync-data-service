package com.caresync.service.data.repositories;

import com.caresync.service.data.entities.Hospital;
import com.caresync.service.data.enums.HOSPITAL_TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByName(String name);
    Boolean existsByName(String name);

    @Query("SELECT h FROM Hospital h JOIN h.types t WHERE t = :type")
    List<Hospital> findByType(@Param("type") HOSPITAL_TYPE type);

}
