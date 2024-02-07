package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vehicle_for_sale")
public class VehicleForSale extends VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal listPrice;

    private double discountPercentage;

    private String optionals;

    private Boolean isNew;

    @Enumerated(EnumType.STRING)
    private StatusSale status;

    public VehicleForSaleDTO toDTO() {
        VehicleForSaleDTO vehicleForSaleDTO = VehicleForSaleDTO.builder()
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

        return vehicleForSaleDTO;

    }



}
