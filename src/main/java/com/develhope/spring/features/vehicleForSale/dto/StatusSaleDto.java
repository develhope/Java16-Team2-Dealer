package com.develhope.spring.features.vehicleForSale.dto;

import com.develhope.spring.features.vehicleForSale.StatusSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusSaleDto {

    private StatusSale status;

}
