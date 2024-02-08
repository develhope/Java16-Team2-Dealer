package com.develhope.spring.orders.forrental;

import com.develhope.spring.vehicle.forrental.VehicleForRental;
import com.develhope.spring.vehicle.forrental.VehicleForRentalDTO;
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
    private RentalOrderService rentalOrderService;


    @PostMapping(path = "/create")
    public ResponseEntity<RentalOrderDTO> addOrder(@RequestBody RentalOrderDTO order) {
        // se esiste id veicolo --> errore veicolo non esistente
        //se esiste id customer --> riprova
        //se customer id è in userDTO allora --> registro il noleggio altrimeti registra nuovo utente

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
