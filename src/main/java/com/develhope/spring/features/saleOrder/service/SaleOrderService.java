package com.develhope.spring.features.saleOrder.service;

import com.develhope.spring.features.saleOrder.StatusPayment;
import com.develhope.spring.features.saleOrder.dto.SaleOrderError;
import com.develhope.spring.features.saleOrder.dto.SaleOrderResponseDto;
import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import com.develhope.spring.features.saleOrder.model.SaleOrderModel;
import com.develhope.spring.features.saleOrder.repository.SaleOrderRepository;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.vehicle.entities.VehicleEntity;
import com.develhope.spring.features.vehicleForSale.StatusSale;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicleForSale.repository.VehicleForSaleRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleOrderService {

    private final SaleOrderRepository saleOrderRepository;
    private final VehicleForSaleRepository vehicleForSaleRepository;



    public Either<SaleOrderError, SaleOrderResponseDto> bookVehicleByCustomer(UserEntity customer, Long vehicleId) {

        if(!customer.getRole().equals(Role.CUSTOMER)) {
            Either.left(new SaleOrderError(403, "User not authorized to book a vehicle"));
        }

        Optional<VehicleForSaleEntity> vehicleOpt = vehicleForSaleRepository.findById(vehicleId);

        if(vehicleOpt.isPresent()) {
            VehicleForSaleEntity vehicle = vehicleOpt.get();

            if(vehicle.getStatus().equals(StatusSale.ORDERABLE) || vehicle.getStatus().equals(StatusSale.READY_FOR_SALE)) {
                SaleOrderEntity saleOrder = SaleOrderEntity.builder()
                        .date(LocalDate.now())
                        .totalPrice(vehicle.getListPrice().multiply((BigDecimal.ONE).subtract(BigDecimal.valueOf(vehicle.getDiscountPercentage()*100))))
                        .customer(customer)
                        .vehicle(vehicle)
                        .statusPayment(StatusPayment.TO_PAY)
                        .build();

                SaleOrderEntity savedOrder = saleOrderRepository.saveAndFlush(saleOrder);

                vehicle.setStatus(StatusSale.BOOKED);
                vehicleForSaleRepository.saveAndFlush(vehicle);

                SaleOrderModel model = SaleOrderModel.convertEntityToModel(savedOrder);
                SaleOrderResponseDto dto = SaleOrderModel.convertModelToResponse(model);
                return Either.right(dto);
            } else {
                return Either.left(new SaleOrderError(460,"Vehicle cannot be booked because the status is: " + vehicle.getStatus().toString())); //VEDERE CODICE DI ERRORE ADEGUATO
            }

        } else {
           return Either.left(new SaleOrderError(404, "Vehicle not found"));
        }

    }
}
