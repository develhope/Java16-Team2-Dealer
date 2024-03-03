package com.develhope.spring.features.saleOrder.service;

import com.develhope.spring.features.saleOrder.dto.SaleOrderError;
import com.develhope.spring.features.saleOrder.dto.SaleOrderResponseDto;
import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import com.develhope.spring.features.saleOrder.model.SaleOrderModel;
import com.develhope.spring.features.saleOrder.repository.SaleOrderRepository;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.vehicle.entities.VehicleEntity;
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


    //VEDERE COME GESTIRE CHE UNA PRENOTAZIONE NON HA UN SELLER MA è NON NULLABLE NELLE TABLE
    public Either<SaleOrderError, SaleOrderResponseDto> bookVehicle(UserEntity customer, Long vehicleId) {

        Optional<VehicleForSaleEntity> vehicleOpt = vehicleForSaleRepository.findById(vehicleId);

        if(vehicleOpt.isPresent()) {
            VehicleForSaleEntity vehicle = vehicleOpt.get();
            SaleOrderEntity saleOrder = SaleOrderEntity.builder()
                    .date(LocalDate.now())
                    .totalPrice(vehicle.getListPrice().multiply((BigDecimal.ONE) .subtract(BigDecimal.valueOf(vehicle.getDiscountPercentage()*100))))
                    .customer(customer)
                    .seller(customer)
                    .vehicle(vehicle)
                    .build();
            //salvarlo

            SaleOrderModel model = SaleOrderModel.convertEntityToModel(saleOrder);
            SaleOrderResponseDto dto = SaleOrderModel.convertModelToResponse(model);
            return Either.right(dto);
        } else {
           return Either.left(new SaleOrderError(404, "Vehicle not found"));
        }

    }
}
