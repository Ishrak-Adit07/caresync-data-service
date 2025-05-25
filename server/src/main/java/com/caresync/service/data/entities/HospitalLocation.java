package com.caresync.service.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hospital_locations")
public class HospitalLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hospital_id", unique = true)
    private Hospital hospital;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Size(max = 100)
    private String thana;

    @NotBlank(message = "PO cannot be blank")
    @Size(max = 100)
    private String po;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "Postal code cannot be blank")
    @Size(max = 20)
    private String postalCode;

    public HospitalLocation(Hospital hospital, String address, String thana, String po, String city, String postalCode) {
        this.hospital = hospital;
        this.address = address;
        this.thana = thana;
        this.po = po;
        this.city = city;
        this.postalCode = postalCode;
    }
}
