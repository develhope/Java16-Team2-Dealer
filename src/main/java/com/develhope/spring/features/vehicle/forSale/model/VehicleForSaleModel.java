package com.develhope.spring.features.vehicle.forSale.model;

import com.develhope.spring.features.vehicle.dto.VehicleRequestDto;
import com.develhope.spring.features.vehicle.entities.VehicleEntity;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleForSaleModel extends VehicleModel {

    private Long id;

    private BigDecimal listPrice;

    private Double discountPercentage;

    private String optionals;

    private Boolean isNew;


    public static VehicleForSaleEntity convertModelToEntity(VehicleForSaleModel vModel) {
        return VehicleForSaleEntity.builder()
                .licensePlate(vModel.getLicensePlate())
                .brand(vModel.getBrand())
                .model(vModel.getModel())
                .displacement(vModel.getDisplacement())
                .color(vModel.getColor())
                .power(vModel.getPower())
                .transmission(vModel.getTransmission())
                .registrationYear(vModel.getRegistrationYear())
                .engine(vModel.getEngine())
                .vehicleType(vModel.getVehicleType())
                .id(vModel.getId())
                .listPrice(vModel.getListPrice())
                .discountPercentage(vModel.getDiscountPercentage())
                .optionals(vModel.getOptionals())
                .isNew(vModel.getIsNew())
                .build();
    }

    public static VehicleForSaleModel convertRequestDtoToModel(VehicleForSaleRequestDto dto) {
        return VehicleForSaleModel.builder()
                .licensePlate(dto.getLicensePlate())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .displacement(dto.getDisplacement())
                .color(dto.getColor())
                .power(dto.getPower())
                .transmission(dto.getTransmission())
                .registrationYear(dto.getRegistrationYear())
                .engine(dto.getEngine())
                .vehicleType(dto.getVehicleType())
                .listPrice(dto.getListPrice())
                .discountPercentage(dto.getDiscountPercentage())
                .optionals(dto.getOptionals())
                .isNew(dto.getIsNew())
                .build();
    }

}


