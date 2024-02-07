package com.develhope.spring.vehicle.forsale;

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
public class VehicleForSaleResponse {

    String statusDescription;
    int statusCode;

    public static ResponseEntity<VehicleForSaleResponse> mapResponseEntity(VehicleForSaleResponse response) {
        HttpStatus status = HttpStatus.resolve(response.statusCode);
        if(status != null) {
            return new ResponseEntity<>(response, status);
        } else {
            return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    static class VehicleForSaleDeletedSuccessfully extends VehicleForSaleResponse {
        public VehicleForSaleDeletedSuccessfully(String statusDescription) {
            super(statusDescription, HttpStatus.NO_CONTENT.value());
        }
    }

    static class VehicleForSaleGenericError extends VehicleForSaleResponse {
        public VehicleForSaleGenericError(String statusDescription) {
            super(statusDescription, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
