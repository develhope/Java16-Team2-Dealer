package com.develhope.spring.orders.forrental;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forrental.VehicleForRentalEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter

public class RentalOrderDTO {


    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyPrice;
    private BigDecimal totalPrice;
    private StatusPayment statusPayment;
    private VehicleForRentalEntity vehicle;
    private UserEntity customer;
    private UserEntity seller;
//
//    private Long vehicle;
//    private Long customer;
//    private Long seller;

    public RentalOrder toEntity() {
        return new RentalOrder(this.id,
                this.startDate,
                this.endDate,
                this.dailyPrice,
                this.totalPrice,
                this.statusPayment,
                this.vehicle,
                this.customer,
                this.seller);
    }

}
