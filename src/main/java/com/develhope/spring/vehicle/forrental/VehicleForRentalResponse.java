package com.develhope.spring.vehicle.forrental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleForRentalResponse {
    String statusDescription;
    int statusCode;

    public static ResponseEntity<VehicleForRentalResponse>
    mapResponseEntity(VehicleForRentalResponse responseRO){
        HttpStatus status = HttpStatus.resolve(responseRO.statusCode);
        if (status != null){
            return new ResponseEntity<>(responseRO, status);
        }else {
            return new ResponseEntity<>(responseRO, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    static class VehicleForRentalDeletedSuccessfully extends VehicleForRentalResponse{
        public VehicleForRentalDeletedSuccessfully(String statusDescription){
            super(statusDescription, HttpStatus.NO_CONTENT.value());
        }
    }
    static class VehicleForRentalGenericError extends VehicleForRentalResponse{
        public VehicleForRentalGenericError(String statusDescription){
        super(statusDescription, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    }
}
