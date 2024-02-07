package com.develhope.spring.vehicle.forrental;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class VehicleForRentalService {
    private VehicleForRentalRepository vehicleForRentalRepository;
    public VehicleForRentalService(VehicleForRentalRepository vehicleForRentalRepository){
        this.vehicleForRentalRepository = vehicleForRentalRepository;
    }
    public VehicleForRentalDTO addVehicleForRental(VehicleForRentalDTO vehicleForRentalDTO){
        VehicleForRental vehicleForRentaleToSave = vehicleForRentalDTO.toEntity();
        VehicleForRental vehicleROSaved = vehicleForRentalRepository.saveAndFlush(vehicleForRentaleToSave);
        return vehicleROSaved.toDto();
    }

    public List<VehicleForRentalDTO> getAllVehiclesForRental() {
        List<VehicleForRental> vehiclesForRental = vehicleForRentalRepository.findAll();
        return vehiclesForRental.stream().map(VehicleForRental::toDTO).toList();
    }

    public Optional<VehicleForRentalDTO> getVehicleForRentalById(Long id) {
        Optional<VehicleForRental> vehicleForRentalSearched = vehicleForRentalRepository.findById(id);
        return vehicleForRentalSearched.map(VehicleForRental::toDTO);
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
