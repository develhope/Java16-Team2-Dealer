package com.develhope.spring.features.vehicle.forSale.entities;

import com.develhope.spring.features.vehicle.entities.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vehicle_for_sale")

public class VehicleForSaleEntity extends VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal listPrice;

    private Double discountPercentage;

    private String optionals;

    private Boolean isNew;


}
