package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.user.UserRepository;
import com.develhope.spring.vehicle.VehicleDTO;
import org.springframework.stereotype.Service;

@Service
public class VehicleForSaleService {

    public VehicleForSaleService(VehicleForSaleRepository vehicleForSaleRepository) {
        this.vehicleForSaleRepository = vehicleForSaleRepository;
    }

    private VehicleForSaleRepository vehicleForSaleRepository;

    public VehicleDTO addVehicleForSale(VehicleDTO vehicle) {
        VehicleForSale vehicleForSale = vehicle.toEntity();
    }
}
