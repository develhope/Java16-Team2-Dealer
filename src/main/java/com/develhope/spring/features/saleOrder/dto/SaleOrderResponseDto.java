package com.develhope.spring.features.saleOrder.dto;

import com.develhope.spring.features.saleOrder.StatusPayment;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SaleOrderResponseDto {

    private Long id;
    private LocalDate date;
    private BigDecimal totalPrice;
    private BigDecimal downPayment;
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;
    private VehicleForSaleEntity vehicle;
    private UserEntity customer;
    private UserEntity seller;

}
