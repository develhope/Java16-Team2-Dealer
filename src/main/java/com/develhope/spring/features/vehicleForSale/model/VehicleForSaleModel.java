package com.develhope.spring.features.vehicleForSale.model;

import com.develhope.spring.features.vehicleForSale.StatusSale;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicle.model.VehicleModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private StatusSale status;


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
                .status(vModel.getStatus())
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
                .status(dto.getStatus())
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
                .status(entity.getStatus())
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
                .status(model.getStatus())
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


