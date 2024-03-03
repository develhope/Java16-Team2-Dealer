package com.develhope.spring.features.vehicle.dto;

import com.develhope.spring.features.vehicle.Engine;
import com.develhope.spring.features.vehicle.Transmission;
import com.develhope.spring.features.vehicle.VehicleType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDto {

    private String licensePlate;

    private String brand;

    private String model;

    private Integer displacement;

    private String color;

    private Double power;

    private Transmission transmission;

    private Year registrationYear;

    private Engine engine;

    private VehicleType vehicleType;


}
