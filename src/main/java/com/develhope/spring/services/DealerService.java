package com.develhope.spring.services;

import com.develhope.spring.domain.entities.enums.UserRole;
import com.develhope.spring.domain.entities.orders.RentalOrder;
import com.develhope.spring.domain.entities.vehicles.VehicleForRental;
import com.develhope.spring.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DealerService {

    private RentalOrderRepository rentalOrderRepository;
    private SaleOrderRepository saleOrderRepository;
    private UserRepository userRepository;
    private VehicleForRentalRepository vehicleForRentalRepository;
    private VehicleForSaleRepository vehicleForSaleRepository;

    public DealerService(RentalOrderRepository rentalOrderRepository, SaleOrderRepository saleOrderRepository,
                         UserRepository userRepository, VehicleForRentalRepository vehicleForRentalRepository,
                         VehicleForSaleRepository vehicleForSaleRepository) {
        this.rentalOrderRepository = rentalOrderRepository;
        this.saleOrderRepository = saleOrderRepository;
        this.userRepository = userRepository;
        this.vehicleForRentalRepository = vehicleForRentalRepository;
        this.vehicleForSaleRepository = vehicleForSaleRepository;
    }


    public Optional<VehicleForRental> addVehicle(VehicleForRental vehicle, Long adminId) {

        if(userRepository.findById(adminId).get().getRole().equals(UserRole.ADMIN)) {
            return Optional.of(vehicleForRentalRepository.save(vehicle));
        } else {
            return Optional.empty();
        }


    }
}
