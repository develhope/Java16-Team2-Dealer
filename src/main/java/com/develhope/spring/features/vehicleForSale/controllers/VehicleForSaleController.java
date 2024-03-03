package com.develhope.spring.features.vehicleForSale.controllers;

import com.develhope.spring.features.vehicleForSale.dto.StatusSaleDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleErrorDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicleForSale.service.VehicleForSaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //TODO MOSTRARE SOLO QUELLI DISPONIBILI E FARE ALTRE ROTTE PER ADMIN CHE VEDA TUTTI GLI STATI
    @Operation(summary = "Get all vehicles for sale")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the list of all vehicles for sale",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(vehicleForSaleService.getAll());
    }

    @GetMapping("/status")
    public ResponseEntity<?> getByStatus(@RequestBody StatusSaleDto status) {

        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByStatus(status);

        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }

    }

}
