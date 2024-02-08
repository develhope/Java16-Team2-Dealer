package com.develhope.spring.orders.forsale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/sale")
public class SaleOrderCustomerController {
    @Autowired
    SaleOrderService saleOrderService;
    @PostMapping(path = "/create")
    public ResponseEntity<SaleOrderDTO> addSale(@RequestBody SaleOrderDTO sale) {
        SaleOrderDTO savedSale = saleOrderService.addSale(sale);
        return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SaleOrderDTO>> getAllSales() {
        return new ResponseEntity<>(saleOrderService.getAllsales(), HttpStatus.OK);
    }

    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Optional<SaleOrderDTO>> getSaleById(@PathVariable long id) {
        Optional<SaleOrderDTO> saleSearched = saleOrderService.getSalesById(id);
        if(saleSearched.isPresent()) {
            return new ResponseEntity<>(saleSearched, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(saleSearched, HttpStatus.NOT_FOUND);
        }
    }
}
