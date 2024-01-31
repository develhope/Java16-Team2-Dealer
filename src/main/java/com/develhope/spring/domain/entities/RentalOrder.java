package com.develhope.spring.domain.entities;

import com.develhope.spring.domain.entities.users.User;
import com.develhope.spring.domain.entities.vehicles.VehicleForRental;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleForRental vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

}
