package com.develhope.spring.LoginAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component

public class SpringMVCConfiguration implements WebMvcConfigurer {
    @Autowired
    private CustomerInterceptor clientInterceptor;
}
