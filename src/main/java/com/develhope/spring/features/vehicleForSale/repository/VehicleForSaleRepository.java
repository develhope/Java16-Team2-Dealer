package com.develhope.spring.features.vehicleForSale.repository;

import com.develhope.spring.features.vehicleForSale.StatusSale;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleForSaleRepository extends JpaRepository<VehicleForSaleEntity, Long> {

    public List<VehicleForSaleEntity> findByStatus(StatusSale status);

}
