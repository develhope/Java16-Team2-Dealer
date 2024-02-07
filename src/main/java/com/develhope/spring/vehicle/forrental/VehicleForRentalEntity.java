package com.develhope.spring.vehicle.forrental;

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
@Table(name = "vehicle_for_rental")
public class VehicleForRentalEntity extends VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal dailyPrice;

    @Enumerated(EnumType.STRING)
    private StatusRental status;

    public VehicleForRentalDTO toDTO() {
        VehicleForRentalDTO vehicleForRentalDTO = VehicleForRentalDTO.builder()
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

        return vehicleForRentalDTO;

    }


}
