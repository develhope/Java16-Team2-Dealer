package com.develhope.spring.loginAuth;


import com.develhope.spring.admins.AdminInterceptor;
import com.develhope.spring.customers.CustomerInterceptor;
import com.develhope.spring.sellers.SellerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringMVCConfiguration implements WebMvcConfigurer {
    @Autowired
    private CustomerInterceptor customerInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private SellerInterceptor sellerInterceptor;

    //    @Override
    public void addInterceptor(InterceptorRegistry registry){
        registry.addInterceptor(customerInterceptor).addPathPatterns("/customer/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(sellerInterceptor).addPathPatterns("/seller/**");
    }
}