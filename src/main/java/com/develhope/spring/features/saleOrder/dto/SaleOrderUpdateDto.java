package com.develhope.spring.features.saleOrder.dto;

import com.develhope.spring.features.saleOrder.StatusPayment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SaleOrderUpdateDto {
    Long id;
    @Enumerated(EnumType.STRING)
    StatusPayment status;
}
