package com.develhope.spring.vehicle.forsale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicleForSale")
public class VehicleForSaleController {

    private VehicleForSaleService vehicleForSaleService;

    public VehicleForSaleController(VehicleForSaleService vehicleForSaleService) {
        this.vehicleForSaleService = vehicleForSaleService;
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleForSaleDTO> addVehicleForSale(@RequestBody VehicleForSaleDTO vehicle) {
        VehicleForSaleDTO vehicleSaved = vehicleForSaleService.addVehicleForSale(vehicle);
        return new ResponseEntity<>(vehicleSaved, HttpStatus.CREATED);
    }


}
