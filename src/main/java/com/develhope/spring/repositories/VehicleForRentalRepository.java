package com.develhope.spring.repositories;

import com.develhope.spring.domain.entities.vehicles.VehicleForRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleForRentalRepository extends JpaRepository<VehicleForRental,Long> {
}
