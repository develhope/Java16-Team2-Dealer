package com.develhope.spring.features.vehicle.forSale.controllers;

import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicle.forSale.service.VehicleForSaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehiclesForSale")
public class VehicleForSaleController {

    private final VehicleForSaleService vehicleForSaleService;

    @Operation(summary = "Add a vehicle for sale")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Creates a new user with the required data",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicleForSale(@RequestBody VehicleForSaleRequestDto dto) {

        return  ResponseEntity.status(HttpStatus.CREATED).body(vehicleForSaleService.addVehicleForSale(dto));

    }

    @Operation(summary = "Get all vehicles for sale")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the list of all vehicles for sale",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(vehicleForSaleService.getAll());
    }



}
