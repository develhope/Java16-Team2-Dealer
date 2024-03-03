package com.develhope.spring.features.saleOrder.repository;

import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrderEntity, Long> {
}
