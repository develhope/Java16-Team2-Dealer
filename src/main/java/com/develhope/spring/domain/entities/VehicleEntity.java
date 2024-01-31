package com.develhope.spring.domain.entities;

import com.develhope.spring.domain.Engine;
import com.develhope.spring.domain.Transmission;
import com.develhope.spring.domain.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public class VehicleEntity {

    @Column(unique = true, nullable = false)
    private String licensePlate;

    private String brand;

    private String model;

    private Integer displacement;

    private String color;

    private double power;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private Year registrationYear;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

}
