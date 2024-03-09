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

import java.math.BigDecimal;
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

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleForSaleService.addVehicleForSale(dto));

    }

    @Operation(summary = "Get all vehicles ready for sale")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the list of all vehicles ready for sale",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @GetMapping("/readyForSale")
    public ResponseEntity<?> getAllReadyForSale() {

        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getAllReadyForSale();

        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }

    }

    @Operation(summary = "Get all orderable vehicles")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the list of all orderable vehicles",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @GetMapping("/orderable")
    public ResponseEntity<?> getAllOrderable() {

        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getAllOrderable();

        if(result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }

    }




    //TODO MOSTRARE SOLO QUELLI DISPONIBILI E FARE ALTRE ROTTE PER ADMIN CHE VEDA TUTTI GLI STATI
    @Operation(summary = "Get all vehicles for sale")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the list of all vehicles for sale",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleForSaleResponseDto.class))})})
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(vehicleForSaleService.getAll());
    }

    @Operation(summary = "Get all vehicles by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get vehicle by specific status"),
            @ApiResponse(responseCode = "404", description = "Vehicles not found")
    })
    @GetMapping("/status")
    public ResponseEntity<?> getByStatus(@RequestBody StatusSaleDto status) {

        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByStatus(status);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }

    }

    @Operation(summary = "Get all vehicles between a given range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found vehicles between a given range"),
            @ApiResponse(responseCode = "404", description = "Vehicles not found")
    })
    @GetMapping("/prices")
    public ResponseEntity<?> getByPrices(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByPrices(minPrice, maxPrice);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
    }

    @Operation(summary = "Get all ORDERABLE and READY FOR SALE vehicles between a given range ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found orderable/sellable vehicles between a given range"),
            @ApiResponse(responseCode = "404", description = "Vehicles not found")
    })
    @GetMapping("/available")
    public ResponseEntity<?> getByPricesAndStatus(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByPricesAndStatus(minPrice, maxPrice);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
    }

    @Operation(summary = "Get all vehicles by brand name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found vehicles with brand name"),
            @ApiResponse(responseCode = "404", description = "Vehicles of certain brand not found")
    })
    @GetMapping("/brand")
    public ResponseEntity<?> getByBrand(@RequestParam String brand) {
        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByBrand(brand);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
    }

    @Operation(summary = "Get all vehicles by model name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found vehicles with model name"),
            @ApiResponse(responseCode = "404", description = "Vehicles of certain model not found")
    })
    @GetMapping("/model")
    public ResponseEntity<?> getByModel(@RequestParam String model) {
        Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> result = vehicleForSaleService.getByModel(model);

        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result.get());
        }
    }

}
