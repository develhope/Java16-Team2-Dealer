package com.develhope.spring.orders.forrental;

import com.develhope.spring.orders.forsale.SaleOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalOrderService {
    private RentalOrderRepository rentalOrderRepository;

    public RentalOrderDTO addOrder(RentalOrderDTO order) {
        RentalOrder orderToSave = order.toEntity();
        RentalOrder orderSaved = rentalOrderRepository.saveAndFlush(orderToSave);
        return orderSaved.toDto();
    }

    public List<RentalOrderDTO> getAllOrder() {
        List<RentalOrder> rentalOrders = rentalOrderRepository.findAll();
        return rentalOrders.stream().map(RentalOrder::toDto).toList();
    }

    public Optional<RentalOrderDTO> getRentalOrdersById(Long id){
        Optional<RentalOrder> orderSearched = rentalOrderRepository.findById(id);
        return orderSearched.map(RentalOrder::toDto);
    }

}
