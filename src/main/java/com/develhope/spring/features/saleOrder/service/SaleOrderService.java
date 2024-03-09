package com.develhope.spring.features.saleOrder.service;

import com.develhope.spring.features.saleOrder.StatusPayment;
import com.develhope.spring.features.saleOrder.dto.SaleOrderError;
import com.develhope.spring.features.saleOrder.dto.SaleOrderRequestDto;
import com.develhope.spring.features.saleOrder.dto.SaleOrderResponseDto;
import com.develhope.spring.features.saleOrder.dto.SaleOrderUpdateDto;
import com.develhope.spring.features.saleOrder.entities.SaleOrderEntity;
import com.develhope.spring.features.saleOrder.model.SaleOrderModel;
import com.develhope.spring.features.saleOrder.repository.SaleOrderRepository;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.repository.UserRepository;
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
    private final UserRepository userRepository;


    public Either<SaleOrderError, SaleOrderResponseDto> bookVehicleByCustomer(UserEntity customer, Long vehicleId) {

        if (!customer.getRole().equals(Role.CUSTOMER)) {
            Either.left(new SaleOrderError(403, "User not authorized to book a vehicle"));
        }

        Optional<VehicleForSaleEntity> vehicleOpt = vehicleForSaleRepository.findById(vehicleId);

        if (vehicleOpt.isPresent()) {
            VehicleForSaleEntity vehicle = vehicleOpt.get();

            if (vehicle.getStatus().equals(StatusSale.ORDERABLE) || vehicle.getStatus().equals(StatusSale.READY_FOR_SALE)) {
                SaleOrderEntity saleOrder = SaleOrderEntity.builder()
                        .date(LocalDate.now())
                        .totalPrice(vehicle.getListPrice().multiply((BigDecimal.ONE).subtract(BigDecimal.valueOf(vehicle.getDiscountPercentage() * 100))))
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
                return Either.left(new SaleOrderError(460, "Vehicle cannot be booked because the status is: " + vehicle.getStatus().toString())); //VEDERE CODICE DI ERRORE ADEGUATO
            }

        } else {
            return Either.left(new SaleOrderError(404, "Vehicle not found"));
        }

    }

    public Either<SaleOrderError, SaleOrderResponseDto> bookOrder(UserEntity seller, SaleOrderRequestDto requestDto) {
        Optional<UserEntity> customerOpt = userRepository.findById(requestDto.getCustomerId());
        if (customerOpt.isPresent()) {
            UserEntity customer = customerOpt.get();
            if (seller.getRole().equals(Role.SELLER) || seller.getRole().equals(Role.ADMIN)) {
                Optional<VehicleForSaleEntity> vehicleOpt = vehicleForSaleRepository.findById(requestDto.getVehicleId());

                if (vehicleOpt.isPresent()) {
                    VehicleForSaleEntity vehicle = vehicleOpt.get();

                    if (vehicle.getStatus().equals(StatusSale.ORDERABLE) || vehicle.getStatus().equals(StatusSale.READY_FOR_SALE)) {
                        SaleOrderEntity saleOrder = SaleOrderEntity.builder()
                                .date(LocalDate.now())
                                .totalPrice(vehicle.getListPrice().multiply((BigDecimal.ONE).subtract(BigDecimal.valueOf(vehicle.getDiscountPercentage() * 100))))
                                .customer(customer)
                                .seller(seller)
                                .vehicle(vehicle)
                                .statusPayment(StatusPayment.TO_PAY)
                                .build();
                        SaleOrderEntity savedOrder = saleOrderRepository.saveAndFlush(saleOrder);

                        vehicle.setStatus(StatusSale.BOOKED);
                        vehicleForSaleRepository.saveAndFlush(vehicle);

                        SaleOrderModel model = SaleOrderModel.convertEntityToModel(savedOrder);
                        SaleOrderResponseDto responseDto = SaleOrderModel.convertModelToResponse(model);
                        return Either.right(responseDto);

                    } else {
                        return Either.left(new SaleOrderError(460, "Vehicle cannot be booked because the status is: "+ vehicle.getStatus().toString()));
                    }
                } else {
                    return Either.left(new SaleOrderError(404, "Vehicle not found"));
                }
            } else {
                return Either.left(new SaleOrderError(403, "User not authorized to book a vehicle"));
            }
        } else {
            return Either.left(new SaleOrderError(404, "Customer not found"));
        }
    }

    public Either<SaleOrderError, SaleOrderResponseDto> updateStatus(UserEntity seller, SaleOrderUpdateDto updateDto) {
        if (seller.getRole().equals(Role.CUSTOMER)) {
            return Either.left(new SaleOrderError(403, "User not authorized"));
        }
        Optional<SaleOrderEntity> saleOrderOpt = saleOrderRepository.findById(updateDto.getId());
        if (saleOrderOpt.isPresent()) {
            SaleOrderEntity saleOrder = saleOrderOpt.get();
            saleOrder.setStatusPayment(updateDto.getStatus());

            SaleOrderEntity savedOrder = saleOrderRepository.saveAndFlush(saleOrder);


            Optional<VehicleForSaleEntity> vehicleOpt = vehicleForSaleRepository.findById(saleOrder.getVehicle().getId());
            VehicleForSaleEntity vehicle = vehicleOpt.get();
            if (updateDto.getStatus().equals(StatusPayment.SETTLED)) {
                vehicle.setStatus(StatusSale.SOLD);
                vehicleForSaleRepository.saveAndFlush(vehicle);
            }
            SaleOrderModel model = SaleOrderModel.convertEntityToModel(savedOrder);
            SaleOrderResponseDto response = SaleOrderModel.convertModelToResponse(model);
            return Either.right(response);
        } else {
            return Either.left(new SaleOrderError(404,"Order not found"));
        }
    }
}
