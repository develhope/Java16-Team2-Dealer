package com.develhope.spring.features.vehicleForSale.service;

import com.develhope.spring.features.vehicleForSale.StatusSale;
import com.develhope.spring.features.vehicleForSale.dto.StatusSaleDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleErrorDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicleForSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicleForSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicleForSale.model.VehicleForSaleModel;
import com.develhope.spring.features.vehicleForSale.repository.VehicleForSaleRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VehicleForSaleService {

    private final VehicleForSaleRepository vehicleForSaleRepository;

    public VehicleForSaleResponseDto addVehicleForSale(VehicleForSaleRequestDto dto) {

        VehicleForSaleModel model = VehicleForSaleModel.convertRequestDtoToModel(dto);
        VehicleForSaleEntity entity = VehicleForSaleModel.convertModelToEntity(model);
        VehicleForSaleEntity savedEntity = vehicleForSaleRepository.saveAndFlush(entity);
        VehicleForSaleModel modelResponse = VehicleForSaleModel.convertEntityToModel(savedEntity);
        VehicleForSaleResponseDto response = VehicleForSaleModel.convertModelToResponse(modelResponse);
        return response;

    }

    public List<VehicleForSaleResponseDto> getAll() {

        List<VehicleForSaleEntity> vehicles = vehicleForSaleRepository.findAll();
        List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(vehicles);
        List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
        return dto;
    }

    public Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> getByStatus(StatusSaleDto status) {

        List<VehicleForSaleEntity> entities = vehicleForSaleRepository.findByStatus(status.getStatus());
        if(!entities.isEmpty()) {
            List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(entities);
            List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
            return Either.right(dto);
        } else {
           return Either.left(new VehicleForSaleErrorDto(404, "Vehicles with status" + status.getStatus().name() + "not found"));
        }

    }

    public Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> getByPrices(BigDecimal minPrice, BigDecimal maxPrice) {

        List<VehicleForSaleEntity> entities = vehicleForSaleRepository.findByListPriceBetween(minPrice, maxPrice);
        if(!entities.isEmpty()) {
            List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(entities);
            List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
            return Either.right(dto);
        } else {
            return Either.left(new VehicleForSaleErrorDto(404, "No vehicles found with price between " + minPrice + " and " + maxPrice));
        }
    }

    public Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> getByPricesAndStatus(BigDecimal minPrice, BigDecimal maxPrice) {

        Set<StatusSale> statuses = new HashSet<>(Arrays.asList(StatusSale.READY_FOR_SALE, StatusSale.ORDERABLE));

        List<VehicleForSaleEntity> entities = vehicleForSaleRepository.findByListPriceBetweenAndStatus(minPrice, maxPrice, statuses);
        if(!entities.isEmpty()) {
            List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(entities);
            List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
            return Either.right(dto);
        } else {
            return Either.left(new VehicleForSaleErrorDto(404, "No available vehicles found with price between " + minPrice + " and " + maxPrice));
        }
    }

    public Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> getByBrand(String brand) {

        List<VehicleForSaleEntity> entities = vehicleForSaleRepository.findByBrand(brand);
        if(!entities.isEmpty()) {
            List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(entities);
            List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
            return Either.right(dto);
        } else {
            return Either.left(new VehicleForSaleErrorDto(404,  brand + " vehicles not found"));
        }

    }

    public Either<VehicleForSaleErrorDto, List<VehicleForSaleResponseDto>> getByModel(String model) {

        List<VehicleForSaleEntity> entities = vehicleForSaleRepository.findByModel(model);
        if(!entities.isEmpty()) {
            List<VehicleForSaleModel> models = VehicleForSaleModel.convertEntityListToModelList(entities);
            List<VehicleForSaleResponseDto> dto = VehicleForSaleModel.convertModelListToResponseList(models);
            return Either.right(dto);
        } else {
            return Either.left(new VehicleForSaleErrorDto(404,  "Model "+ model +" vehicles not found"));
        }

    }
}
