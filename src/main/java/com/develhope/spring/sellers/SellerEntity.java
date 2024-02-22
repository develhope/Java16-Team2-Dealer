package com.develhope.spring.sellers;

import com.develhope.spring.orders.forrental.RentalOrder;
import com.develhope.spring.orders.forsale.SaleOrder;
import com.develhope.spring.user.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellerEntity extends UserEntity {

    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.ALL)
    private List<SaleOrder> saleOrderList;

    @OneToMany(mappedBy = "sellerId", cascade = CascadeType.ALL)
    private List<RentalOrder> rentalOrderList;

}
