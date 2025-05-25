//package com.caresync.service.data.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "hospitals")
//public class Hospital {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank(message = "Hospital name cannot be blank")
//    @Size(max = 150, message = "Hospital name must be at most 150 characters")
//    private String name;
//
//    @Size(max = 20, message = "Phone number must be at most 20 characters")
//    private String phoneNumber;
//
//    @Size(max = 255, message = "Website URL must be at most 255 characters")
//    private String website;
//
//    @OneToOne(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
//    private HospitalLocation location;
//
//    @ElementCollection(targetClass = HospitalType.class)
//    @CollectionTable(name = "hospital_types", joinColumns = @JoinColumn(name = "hospital_id"))
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type")
//    @NotEmpty(message = "At least one hospital type must be specified")
//    private List<HospitalType> types;
//
//    @Min(value = 0, message = "Number of ICUs cannot be negative")
//    private Short icus;
//
//    public Hospital(String name, String phoneNumber, String website, List<HospitalType> types, Short icus) {
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.website = website;
//        this.types = types;
//        this.icus = icus;
//    }
//}
