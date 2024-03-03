package com.develhope.spring.features.saleOrder.model;

import com.develhope.spring.features.saleOrder.StatusPayment;
import com.develhope.spring.features.saleOrder.dto.SaleOrderResponseDto;
import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import jakarta.persistence.*;
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
public class SaleOrderModel {

    private Long id;
    private LocalDate date;
    private BigDecimal totalPrice;
    private BigDecimal downPayment;
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;
    private VehicleForSaleEntity vehicle;
    private UserEntity customer;
    private UserEntity seller;

    public static SaleOrderModel convertEntityToModel(SaleOrderEntity entity) {
        return SaleOrderModel.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .totalPrice(entity.getTotalPrice())
                .downPayment(entity.getDownPayment())
                .statusPayment(entity.getStatusPayment())
                .vehicle(entity.getVehicle())
                .customer(entity.getCustomer())
                .seller(entity.getSeller())
                .build();
    }

    public static SaleOrderResponseDto convertModelToResponse(SaleOrderModel model) {
        return SaleOrderResponseDto.builder()
                .id(model.getId())
                .date(model.getDate())
                .totalPrice(model.getTotalPrice())
                .downPayment(model.getDownPayment())
                .statusPayment(model.getStatusPayment())
                .vehicle(model.getVehicle())
                .customer(model.getCustomer())
                .seller(model.getSeller())
                .build();
    }

}
