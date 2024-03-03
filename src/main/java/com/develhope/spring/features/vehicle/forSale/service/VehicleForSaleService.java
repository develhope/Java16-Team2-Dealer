package com.develhope.spring.features.vehicle.forSale.service;

import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleRequestDto;
import com.develhope.spring.features.vehicle.forSale.dto.VehicleForSaleResponseDto;
import com.develhope.spring.features.vehicle.forSale.entities.VehicleForSaleEntity;
import com.develhope.spring.features.vehicle.forSale.model.VehicleForSaleModel;
import com.develhope.spring.features.vehicle.forSale.repository.VehicleForSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
