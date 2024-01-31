package com.develhope.spring.domain.entities.vehicles;

import com.develhope.spring.domain.entities.enums.StatusRental;
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
public class VehicleForRental extends VehicleEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal hourlyPrice;

    @Enumerated(EnumType.STRING)
    private StatusRental status;



}
