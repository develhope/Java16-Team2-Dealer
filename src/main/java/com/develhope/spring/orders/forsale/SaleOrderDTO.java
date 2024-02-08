package com.develhope.spring.orders.forsale;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SaleOrderDTO {
    private Long id;
    private LocalDate date;
    private BigDecimal totalPrice;
    private BigDecimal downPayment;
    private StatusPayment statusPayment;
    private VehicleForSale vehicle;
    private UserEntity customer;
    private UserEntity seller;

    public SaleOrder toEntity() {
        return new SaleOrder(this.id, this.date, this.totalPrice, this.downPayment, this.statusPayment, this.vehicle,
                this.customer, this.seller);
    }
}
