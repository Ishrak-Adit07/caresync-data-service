package com.caresync.service.data.entities;

import com.caresync.service.data.dtos.data.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctors")
public class Doctor {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @ElementCollection
    @CollectionTable(name = "doctor_specialties", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "specialty")
    private List<String> specialties;

    private String phoneNumber;
    private String email;

    private String locationId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Transient
    private Location location; // DTO only
}
