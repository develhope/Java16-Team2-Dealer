package com.develhope.spring.vehicle.forrental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleForRentalRepository extends JpaRepository<VehicleForRentalEntity,Long> {
}
