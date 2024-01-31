package com.develhope.spring.controllers;

import com.develhope.spring.domain.entities.vehicles.VehicleForRental;
import com.develhope.spring.services.DealerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/rental")
public class RentalController {

    private DealerService dealerService;

    public RentalController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping("/admin/{adminId}/addVehicle")
    public Optional<VehicleForRental> addVehicle(@PathVariable Long adminId,
                                                 @RequestBody VehicleForRental vehicle) {
        return dealerService.addVehicle(vehicle, adminId);
    }
}
