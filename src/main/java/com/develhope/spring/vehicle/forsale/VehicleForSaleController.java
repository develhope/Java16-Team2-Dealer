package com.develhope.spring.vehicle.forsale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleForSaleDTO>> getAllUsers() {
        return new ResponseEntity<>(vehicleForSaleService.getAllVehiclesForSale(), HttpStatus.OK);
    }

    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Optional<VehicleForSaleDTO>> getUserById(@PathVariable long id) {
        Optional<VehicleForSaleDTO> vehicleForSaleSearched = vehicleForSaleService.getVehicleForSaleById(id);
        if(vehicleForSaleSearched.isPresent()) {
            return new ResponseEntity<>(vehicleForSaleSearched, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(vehicleForSaleSearched, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleForSaleResponse> deleteVehicleForSaleById(@PathVariable Long id) {
        return vehicleForSaleService.deleteVehicleForSaleById(id);
    }

}
