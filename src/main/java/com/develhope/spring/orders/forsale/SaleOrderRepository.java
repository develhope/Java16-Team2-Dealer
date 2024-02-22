package com.develhope.spring.orders.forsale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {
   // @Query(value= "UPDATE la tabella saleOrderdove saleOrderStatus = "canceled"
   // con id = idSaleOrder e saleOrder = Order)
    void updateDeletedById(@Param(("saleOrderId")) Long saleOrderId);
}
