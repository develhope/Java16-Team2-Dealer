package com.develhope.spring.vehicle.forrental;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicleForRental")
public class VehicleForRentalController {
    private VehicleForRentalService vehicleForRentalService;
}
