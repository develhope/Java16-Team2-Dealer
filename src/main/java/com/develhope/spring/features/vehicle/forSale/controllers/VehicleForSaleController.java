package com.develhope.spring.features.vehicle.forSale.controllers;

import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.service.VehicleForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehiclesForSale")
public class VehicleForSaleController {

    private final VehicleForSaleService vehicleForSaleService;

    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicleForSale(@RequestBody VehicleForSaleRequestDto dto) {

        return  ResponseEntity.status(HttpStatus.CREATED).body(vehicleForSaleService.addVehicleForSale(dto));

    }



}
