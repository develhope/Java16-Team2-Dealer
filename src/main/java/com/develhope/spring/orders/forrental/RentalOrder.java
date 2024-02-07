package com.develhope.spring.orders.forrental;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forrental.VehicleForRentalEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    //@Column(nullable = false)
    //   private BigDecimal dailyPrice;
    @Column(nullable = false)
    private BigDecimal totalPrice;

    // cos è??
    private BigDecimal downPayment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleForRentalEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    public RentalOrderDTO toDto() {
        return new RentalOrderDTO(
                this.id,
                this.startDate,
                this.endDate,
                this.totalPrice,
                this.downPayment,
                this.statusPayment,
                this.vehicle,
                this.customer,
                this.seller);

    }

}
