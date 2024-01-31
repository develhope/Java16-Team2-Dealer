package com.develhope.spring.repositories;

import com.develhope.spring.domain.entities.vehicles.VehicleForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleForSaleRepository extends JpaRepository<VehicleForSale,Long> {
}
