package com.develhope.spring.repositories;

import com.develhope.spring.domain.entities.orders.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {
}
