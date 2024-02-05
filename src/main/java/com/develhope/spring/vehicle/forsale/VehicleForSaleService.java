package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.user.UserDTO;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.user.UserRepository;
import com.develhope.spring.vehicle.VehicleDTO;
import org.springframework.stereotype.Service;

@Service
public class VehicleForSaleService {

    public VehicleForSaleService(VehicleForSaleRepository vehicleForSaleRepository) {
        this.vehicleForSaleRepository = vehicleForSaleRepository;
    }

    private VehicleForSaleRepository vehicleForSaleRepository;

    public VehicleForSaleDTO addVehicleForSale(VehicleForSaleDTO vehicleForSaleDTO) {
        VehicleForSale vehicleForSaleToSave = vehicleForSaleDTO.toEntity();
        VehicleForSale vehicleSaved = vehicleForSaleRepository.saveAndFlush(vehicleForSaleToSave);
        return vehicleSaved.toDTO();
    }



}
