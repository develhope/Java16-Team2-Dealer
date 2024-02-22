package com.develhope.spring.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dealer/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //ORDER OPERATION
    @PostMapping("/order/client/create/{id_client}")
    public void createOrder(){}

    @DeleteMapping("/order/client/delete/{idOrder}")
    public void deleteOrderById(){}

    @PatchMapping("/order/client/update/{idOrder}")
    public void updateOrderById(){}

    // RENT OPERATION
    @PostMapping("/rent/client/create")
    public void createRent(){}

    @DeleteMapping("/rent/client/delete/{idRent}")
    public void deleteRentById(){}

    @PatchMapping("/rent/client/update/{idRent}")
    public void updateRentById(){}


}
