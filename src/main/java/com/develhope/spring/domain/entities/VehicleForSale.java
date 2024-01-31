package com.develhope.spring.domain.entities;

import com.develhope.spring.domain.StatusSale;
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

    private StatusSale status;

}
