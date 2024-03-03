package com.develhope.spring.features.saleOrder.controllers;

import com.develhope.spring.features.saleOrder.service.SaleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/saleOrders")
public class SaleOrderController {

    private final SaleOrderService saleOrderService;


}
