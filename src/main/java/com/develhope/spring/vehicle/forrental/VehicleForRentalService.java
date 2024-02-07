package com.develhope.spring.vehicle.forrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class VehicleForRentalService {
    @Autowired
    private VehicleForRentalRepository vehicleForRentalRepository;
    public VehicleForRentalService(VehicleForRentalRepository vehicleForRentalRepository){
        this.vehicleForRentalRepository = vehicleForRentalRepository;
    }
    public VehicleForRentalDTO addVehicleForRental(VehicleForRentalDTO vehicleForRentalDTO){
        VehicleForRentalEntity vehicleForRentalToSaveEntity = vehicleForRentalDTO.toEntity();
        VehicleForRentalEntity vehicleROSaved = vehicleForRentalRepository.saveAndFlush(vehicleForRentalToSaveEntity);
        return vehicleROSaved.toDTO();
    }

    public List<VehicleForRentalDTO> getAllVehiclesForRental() {
        List<VehicleForRentalEntity> vehiclesForRental = vehicleForRentalRepository.findAll();
        return vehiclesForRental.stream().map(VehicleForRentalEntity::toDTO).toList();
    }

    public Optional<VehicleForRentalDTO> getVehicleForRentalById(Long id) {
        Optional<VehicleForRentalEntity> vehicleForRentalSearched = vehicleForRentalRepository.findById(id);
        return vehicleForRentalSearched.map(VehicleForRentalEntity::toDTO);
    }

    ResponseEntity<VehicleForRentalResponse> deleteVehicleForRentalById(Long id) {
        boolean userExist = vehicleForRentalRepository.existsById(id);
        if (userExist) {
            vehicleForRentalRepository.deleteById(id);
            VehicleForRentalResponse.VehicleForRentalDeletedSuccessfully response =
                    new VehicleForRentalResponse.VehicleForRentalDeletedSuccessfully("Vehicle for rental successfully deleted.");

            return VehicleForRentalResponse.mapResponseEntity(response);
        } else {
            VehicleForRentalResponse.VehicleForRentalGenericError response =
                    new VehicleForRentalResponse.VehicleForRentalGenericError("Error while deleting vehicle for rental with ID: " + id);

            return VehicleForRentalResponse.mapResponseEntity(response);
        }
    }
}
