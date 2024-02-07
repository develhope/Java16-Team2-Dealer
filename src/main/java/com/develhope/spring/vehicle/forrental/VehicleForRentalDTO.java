package com.develhope.spring.vehicle.forrental;

import com.develhope.spring.vehicle.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleForRentalDTO extends VehicleDTO {

    private Long id;

    private BigDecimal dailyPrice;

    private StatusRental status;


    public VehicleForRentalEntity toEntity() {

        VehicleForRentalEntity vehicleForRentalEntity = VehicleForRentalEntity.builder()
                .licensePlate(this.getLicensePlate())
                .brand(this.getBrand())
                .model(this.getModel())
                .displacement(this.getDisplacement())
                .color(this.getColor())
                .power(this.getPower())
                .transmission(this.getTransmission())
                .registrationYear(this.getRegistrationYear())
                .engine(this.getEngine())
                .type(this.getType())
                .id(this.id)
                .dailyPrice(this.dailyPrice)
                .status(this.status)
                .build();
        return vehicleForRentalEntity;
    }



}
