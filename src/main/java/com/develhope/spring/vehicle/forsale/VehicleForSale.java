package com.develhope.spring.vehicle.forsale;

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
@Table(name = "Vehicle for Sale")
public class VehicleForSale extends VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal listPrice;

    private double discountPercentage;

    private String optionals;

    private Boolean isNew;

    @Enumerated(EnumType.STRING)
    private StatusSale status;

}
