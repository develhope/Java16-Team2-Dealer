package com.develhope.spring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @PostLoad
    private void postLoad() {
        this.totalPrice = vehicle.getHourlyPrice().multiply(BigDecimal.valueOf(ChronoUnit.HOURS.between(start,end)));
    }


}
