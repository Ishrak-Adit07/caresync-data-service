package com.caresync.service.data.entities;

import com.caresync.service.data.dtos.data.Location;
import com.caresync.service.data.enums.HOSPITAL_TYPE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hospital name cannot be blank")
    @Size(max = 150)
    private String name;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 255)
    private String website;

    private Long locationId;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hospital_types", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "type")
    @NotEmpty(message = "At least one hospital type must be specified")
    private List<HOSPITAL_TYPE> types;

    @Min(0)
    private Short icus;

    @Transient
    private Location location;
}
