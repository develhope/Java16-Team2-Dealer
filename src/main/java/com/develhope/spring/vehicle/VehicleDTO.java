package com.develhope.spring.vehicle;

import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.Year;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleDTO {

    private String licensePlate;

    private String brand;

    private String model;

    private Integer displacement;

    private String color;

    private double power;

    private Transmission transmission;

    private Year registrationYear;

    private Engine engine;

    private VehicleType type;

    public VehicleEntity toEntity() {
        return new VehicleEntity(this.licensePlate, this.brand, this.model, this.displacement, this.color,
                this.power, this.transmission, this.registrationYear, this.engine, this.type);
    }
}
