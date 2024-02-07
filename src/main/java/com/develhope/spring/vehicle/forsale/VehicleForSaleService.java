package com.develhope.spring.vehicle.forsale;

import com.develhope.spring.user.UserDTO;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.user.UserRepository;
import com.develhope.spring.vehicle.VehicleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<VehicleForSaleDTO> getAllVehiclesForSale() {
        List<VehicleForSale> vehiclesForSale = vehicleForSaleRepository.findAll();
        return vehiclesForSale.stream().map(VehicleForSale::toDTO).toList();
    }

    public Optional<VehicleForSaleDTO> getVehicleForSaleById(Long id) {
        Optional<VehicleForSale> vehicleForSaleSearched = vehicleForSaleRepository.findById(id);
        return vehicleForSaleSearched.map(VehicleForSale::toDTO);
    }

    ResponseEntity<VehicleForSaleResponse> deleteVehicleForSaleById(Long id) {
        boolean userExist = vehicleForSaleRepository.existsById(id);
        if (userExist) {
            vehicleForSaleRepository.deleteById(id);
            VehicleForSaleResponse.VehicleForSaleDeletedSuccessfully response =
                    new VehicleForSaleResponse.VehicleForSaleDeletedSuccessfully("Vehicle for sale successfully deleted.");

            return VehicleForSaleResponse.mapResponseEntity(response);
        } else {
            VehicleForSaleResponse.VehicleForSaleGenericError response =
                    new VehicleForSaleResponse.VehicleForSaleGenericError("Error while deleting vehicle for sale with ID: " + id);

            return VehicleForSaleResponse.mapResponseEntity(response);
        }
    }

}
