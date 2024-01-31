package com.develhope.spring.domain.entities.vehicles;

import com.develhope.spring.domain.entities.enums.StatusSale;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Sale")
public class VehicleForSale extends VehicleEntity{

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
