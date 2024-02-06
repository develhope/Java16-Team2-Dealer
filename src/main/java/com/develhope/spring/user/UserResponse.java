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

    int statusCode;


    public static ResponseEntity<UserResponse> mapResponseEntity(UserResponse response) {
        HttpStatus status = HttpStatus.resolve(response.statusCode);
        if(status != null) {
            return new ResponseEntity<>(response, status);
        } else {
            return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    static class UserDeletedSuccessfully extends UserResponse {
        public UserDeletedSuccessfully(String statusDescription) {
            super(statusDescription, HttpStatus.NO_CONTENT.value());
        }
    }

    static class UserGenericError extends UserResponse {
        public UserGenericError(String statusDescription) {
            super(statusDescription, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }



}
