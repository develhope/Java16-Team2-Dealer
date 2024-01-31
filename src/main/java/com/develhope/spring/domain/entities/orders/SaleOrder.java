package com.develhope.spring.domain.entities.orders;

import com.develhope.spring.domain.entities.enums.StatusPayment;
import com.develhope.spring.domain.entities.users.User;
import com.develhope.spring.domain.entities.vehicles.VehicleForRental;
import com.develhope.spring.domain.entities.vehicles.VehicleForSale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private User customer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}
