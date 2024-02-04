package com.develhope.spring.vehicle.forrental;

import com.develhope.spring.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Vehicle for Rental")
public class VehicleForRental extends VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal hourlyPrice;

    @Enumerated(EnumType.STRING)
    private StatusRental status;



}
