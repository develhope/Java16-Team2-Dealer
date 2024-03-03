package com.develhope.spring.features.saleOrder.controllers;

import com.develhope.spring.features.saleOrder.dto.SaleOrderError;
import com.develhope.spring.features.saleOrder.dto.SaleOrderResponseDto;
import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import com.develhope.spring.features.saleOrder.service.SaleOrderService;
import com.develhope.spring.features.user.entities.UserEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saleOrders")
public class SaleOrderController {

    private final SaleOrderService saleOrderService;

   @PostMapping("/{vehicleId}")
    ResponseEntity<?> bookVehicle(@AuthenticationPrincipal UserEntity customer,
                                  @PathVariable Long vehicleId) {
       Either<SaleOrderError, SaleOrderResponseDto> result = saleOrderService.bookVehicle(customer, vehicleId);
       if(result.isLeft()) {
           return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
       } else {
           return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
       }
   }

}