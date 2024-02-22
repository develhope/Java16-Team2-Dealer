package com.develhope.spring.orders.forrental;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.vehicle.forrental.VehicleForRentalEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "rents")
@Getter
@Setter
public class RentalOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    //acconto
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
    private UserEntity sellerId;

    private Boolean rentable;

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
                this.sellerId,
                this.rentable);
    }
}

