package com.develhope.spring.orders.forsale;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forsale.StatusSale;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleForSale vehicleId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customerId;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity sellerId;

    @Column(name = "sale_status" , nullable = false)
    private StatusSale statusSale;
    @Column(name = "order_status" , nullable = false)
    private SaleOrderStatus saleOrderStatus;


    @Column(name = "statement" , nullable = false)
    private SaleOrderStatement saleOrderStatement;
}
