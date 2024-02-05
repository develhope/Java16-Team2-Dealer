package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.vehicle.VehicleDTO;
import com.develhope.spring.vehicle.VehicleEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleForSaleDTO extends VehicleDTO {

    private Long id;

    private BigDecimal listPrice;

    private double discountPercentage;

    private String optionals;

    private Boolean isNew;

    private StatusSale status;

    @Override
    public VehicleEntity toEntity() {
        return super.toEntity();
    }

    /*@Override
    public VehicleForSale toEntity() {
        return new VehicleForSale(this.id, this.listPrice, this.discountPercentage, this.optionals, this.isNew, this.status,
                this.getLicensePlate(), this.getBrand(), this.getModel(), this.getDisplacement(), this.getColor(),
                this.getPower(), this.getTransmission(), this.getRegistrationYear(), this.getEngine(), this.getType());
    }*/

}
