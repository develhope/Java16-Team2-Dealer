package com.develhope.spring.user;

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
public class UserResponse {

    String statusDescription;

    Integer statusCode;


    public static ResponseEntity<UserResponse> mapResponseEntity(UserResponse response) {
        HttpStatus status = HttpStatus.resolve(response.statusCode);
        if(status != null) {
            return new ResponseEntity<>(response, status);
        } else {
            return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
        }
    }



}
