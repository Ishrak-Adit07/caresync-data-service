package com.caresync.service.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor_hospital")
public class DoctorHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ElementCollection
    @CollectionTable(name = "doctor_appointments", joinColumns = @JoinColumn(name = "doctor_hospital_id"))
    @Column(name = "appointment_time")
    private List<LocalDateTime> appointmentTimes;

    @ElementCollection
    @CollectionTable(name = "doctor_schedule", joinColumns = @JoinColumn(name = "doctor_hospital_id"))
    @Column(name = "weekly_schedule")
    private List<String> weeklySchedule;

    private BigDecimal appointmentFee;
}
