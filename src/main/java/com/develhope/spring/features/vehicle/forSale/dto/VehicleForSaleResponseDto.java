package com.develhope.spring.features.vehicle.forSale.dto;

import com.develhope.spring.features.vehicle.dto.VehicleResponseDto;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class VehicleForSaleResponseDto extends VehicleResponseDto {

    private Long id;

    private BigDecimal listPrice;

    private Double discountPercentage;

    private String optionals;

    private Boolean isNew;

}
