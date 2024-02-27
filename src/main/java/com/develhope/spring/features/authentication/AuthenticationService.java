package com.develhope.spring.features.authentication;

import com.develhope.spring.features.authentication.dto.request.SignUpRequest;
import com.develhope.spring.features.authentication.dto.request.SigninRequest;
import com.develhope.spring.features.authentication.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
