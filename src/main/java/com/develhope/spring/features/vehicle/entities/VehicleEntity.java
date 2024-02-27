package com.develhope.spring.features.vehicle.entities;

import com.develhope.spring.features.vehicle.Engine;
import com.develhope.spring.features.vehicle.Transmission;
import com.develhope.spring.features.vehicle.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class VehicleEntity {

    @Column(unique = true)
    private String licensePlate;

    private String brand;

    private String model;

    private Integer displacement;

    private String color;

    private Double power;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Year registrationYear;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


}
