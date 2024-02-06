package com.develhope.spring.orders.forsale;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserDTO;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table
public class SaleOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    private BigDecimal downPayment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleForSale vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    public SaleOrderDTO toDto() {
        return new SaleOrderDTO(this.id, this.date, this.totalPrice, this.downPayment, this.statusPayment, this.vehicle,
                this.customer, this.seller);
    }
}
