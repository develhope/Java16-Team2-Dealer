package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.vehicle.VehicleDTO;
import com.develhope.spring.vehicle.VehicleEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleForSaleDTO extends VehicleDTO {

    private Long id;

    private BigDecimal listPrice;

    private double discountPercentage;

    private String optionals;

    private Boolean isNew;

    private StatusSale status;


    public VehicleForSale toEntity() {

        VehicleForSale vehicleForSale = VehicleForSale.builder()
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
                .listPrice(this.listPrice)
                .discountPercentage(this.discountPercentage)
                .optionals(this.optionals)
                .isNew(this.isNew)
                .status(this.status)
                .build();
        return vehicleForSale;
    }



}
