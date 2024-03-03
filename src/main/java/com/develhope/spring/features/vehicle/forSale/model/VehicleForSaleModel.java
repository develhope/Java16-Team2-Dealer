package com.develhope.spring.features.vehicle.forSale.model;

import com.develhope.spring.features.user.dto.UserResponseDto;
import com.develhope.spring.features.user.entities.UserEntity;
import com.develhope.spring.features.user.model.UserModel;
import com.develhope.spring.features.vehicle.dto.VehicleRequestDto;
import com.develhope.spring.features.vehicle.entities.VehicleEntity;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicle.forSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

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
                .id(vModel.getId())
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

    public static VehicleForSaleModel convertEntityToModel(VehicleForSaleEntity entity) {
        return VehicleForSaleModel.builder()
                .id(entity.getId())
                .licensePlate(entity.getLicensePlate())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .displacement(entity.getDisplacement())
                .color(entity.getColor())
                .power(entity.getPower())
                .transmission(entity.getTransmission())
                .registrationYear(entity.getRegistrationYear())
                .engine(entity.getEngine())
                .vehicleType(entity.getVehicleType())
                .listPrice(entity.getListPrice())
                .discountPercentage(entity.getDiscountPercentage())
                .optionals(entity.getOptionals())
                .isNew(entity.getIsNew())
                .build();
    }

    public static VehicleForSaleResponseDto convertModelToResponse(VehicleForSaleModel model) {
        return VehicleForSaleResponseDto.builder()
                .id(model.getId())
                .licensePlate(model.getLicensePlate())
                .brand(model.getBrand())
                .model(model.getModel())
                .displacement(model.getDisplacement())
                .color(model.getColor())
                .power(model.getPower())
                .transmission(model.getTransmission())
                .registrationYear(model.getRegistrationYear())
                .engine(model.getEngine())
                .vehicleType(model.getVehicleType())
                .listPrice(model.getListPrice())
                .discountPercentage(model.getDiscountPercentage())
                .optionals(model.getOptionals())
                .isNew(model.getIsNew())
                .build();
    }


    public static List<VehicleForSaleModel> convertEntityListToModelList(List<VehicleForSaleEntity> entities) {
        return entities
                .stream()
                .map(VehicleForSaleModel::convertEntityToModel)
                .toList();
    }

    public static List<VehicleForSaleResponseDto> convertModelListToResponseList(List<VehicleForSaleModel> models) {
        return models
                .stream()
                .map(VehicleForSaleModel::convertModelToResponse)
                .toList();
    }


}


