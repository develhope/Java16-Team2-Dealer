package com.develhope.spring.features.vehicleForSale.repository;

import com.develhope.spring.features.vehicleForSale.StatusSale;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface VehicleForSaleRepository extends JpaRepository<VehicleForSaleEntity, Long> {

    List<VehicleForSaleEntity> findByStatus(StatusSale status);

    List<VehicleForSaleEntity> findByListPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    //finds only sellable and orderable vehicles in a given range by adding status
    //if a vehicle is not sellable/orderable but it s in the price range, it wont be counted
    @Query("SELECT v FROM VehicleForSaleEntity v WHERE v.listPrice BETWEEN :minPrice AND :maxPrice AND v.status IN :statuses")
    List<VehicleForSaleEntity> findByListPriceBetweenAndStatus(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("statuses") Set<StatusSale> statuses);

    List<VehicleForSaleEntity> findByBrand(String brand);

    List<VehicleForSaleEntity> findByModel(String model);

}
