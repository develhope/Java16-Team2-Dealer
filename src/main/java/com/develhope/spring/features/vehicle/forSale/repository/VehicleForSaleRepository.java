package com.develhope.spring.features.vehicle.forSale.repository;

import com.develhope.spring.features.vehicle.forSale.entities.VehicleForSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleForSaleRepository extends JpaRepository<VehicleForSaleEntity, Long> {
}
