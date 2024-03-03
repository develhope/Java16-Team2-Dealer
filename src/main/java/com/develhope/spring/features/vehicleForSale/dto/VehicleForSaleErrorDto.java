package com.develhope.spring.features.vehicleForSale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleForSaleErrorDto {

    private int code;
    private String message;

}