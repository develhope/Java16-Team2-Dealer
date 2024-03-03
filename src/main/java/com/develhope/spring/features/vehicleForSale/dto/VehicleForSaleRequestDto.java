package com.develhope.spring.features.vehicleForSale.dto;

import com.develhope.spring.features.vehicle.dto.VehicleRequestDto;
import com.develhope.spring.features.vehicleForSale.StatusSale;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleForSaleRequestDto extends VehicleRequestDto {

    private BigDecimal listPrice;

    private Double discountPercentage;

    private String optionals;

    private Boolean isNew;

    @Enumerated(EnumType.STRING)
    private StatusSale status;

}
