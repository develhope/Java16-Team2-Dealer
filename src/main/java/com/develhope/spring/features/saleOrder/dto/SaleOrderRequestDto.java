package com.develhope.spring.features.saleOrder.dto;

import com.develhope.spring.features.saleOrder.StatusPayment;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderRequestDto {

    private BigDecimal downPayment;
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;
    private Long vehicleId;
    private Long customerId;



}
