package com.develhope.spring.orders.forsale;

import com.develhope.spring.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/sale")
public class SaleOrderController {
    @Autowired
    SaleOrderService saleOrderService;
    @PostMapping(path = "/create")
    public SaleOrder createSaleOrder(@RequestBody SaleOrder order,
                                     @RequestParam (name = "vehicle_id") Long vehicle_id,
                                     @RequestParam(name = "customer_id") Long customer_id,
                                     @RequestParam(name = "seller_id") Long seller_id) {
        return saleOrderService.createSaleOrder(order, vehicle_id, customer_id, seller_id);
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<String> payOrder(@PathVariable Long orderId) {
        boolean isPaid = saleOrderService.payOrder(orderId);
        if (isPaid) {
            return ResponseEntity.ok("Paid successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error while paying the order.");
        }
    }


    /*@GetMapping("/getAll")
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
    }*/
}
