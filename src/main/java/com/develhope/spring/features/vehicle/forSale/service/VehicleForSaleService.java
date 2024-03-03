package com.develhope.spring.features.vehicle.forSale.service;

import com.develhope.spring.features.vehicle.forSale.StatusSale;
import com.develhope.spring.features.vehicle.forSale.dto.StatusSaleDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleErrorDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicle.forSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicle.forSale.model.VehicleForSaleModel;
import com.develhope.spring.features.vehicle.forSale.repository.VehicleForSaleRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
