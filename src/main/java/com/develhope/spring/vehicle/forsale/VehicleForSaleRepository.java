package com.develhope.spring.vehicle.forsale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleForSaleRepository extends JpaRepository<VehicleForSale,Long> {
}
