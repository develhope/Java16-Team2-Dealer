package com.develhope.spring.orders.forrental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/rental-order")
public class RentalOrderAdminController {
    // vista di tutti i noleggi
    // creare noleggio per utente X,
    // cancellare noleggio per utente X,
    // modificare noleggio per utente X
    @Autowired
    private RentalOrderRepository rentalOrderRepository;
    @Autowired
    private RentalOrderService rentalOrderService;

    @PostMapping(path = "/create")
    public ResponseEntity<RentalOrderDTO> addOrder(@RequestBody RentalOrderDTO order) {
        RentalOrderDTO savedOrder = rentalOrderService.addOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RentalOrderDTO>> getAllOrder(){
        return new ResponseEntity<>(rentalOrderService.getAllOrder(), HttpStatus.OK);
    }
    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Optional<RentalOrderDTO>> getRentalOrderById(@PathVariable Long id){
        Optional<RentalOrderDTO> rentalSearched = rentalOrderService.getRentalOrdersById(id);
        if(rentalSearched.isPresent()){
            return new ResponseEntity<>(rentalSearched, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(rentalSearched, HttpStatus.NOT_FOUND);
        }
    }
}
