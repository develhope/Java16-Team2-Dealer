package com.develhope.spring.repositories;

import com.develhope.spring.domain.entities.orders.RentalOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOrderRepository extends JpaRepository<RentalOrder,Long> {
}
