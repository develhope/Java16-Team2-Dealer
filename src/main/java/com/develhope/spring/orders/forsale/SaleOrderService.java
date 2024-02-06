package com.develhope.spring.orders.forsale;

import com.develhope.spring.user.UserDTO;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleOrderService {

    @Autowired
    SaleOrderRepository saleOrderRepository;

    public SaleOrderDTO addSale(SaleOrderDTO sale) {

        SaleOrder saleToSave = sale.toEntity();

        SaleOrder saleSaved = saleOrderRepository.saveAndFlush(saleToSave);
        return saleSaved.toDto();
    }

    public List<SaleOrderDTO> getAllsales() {
        List<SaleOrder> saleOrders = saleOrderRepository.findAll();
        return saleOrders.stream().map(SaleOrder::toDto).toList();
    }

    public Optional<SaleOrderDTO> getSalesById(Long id) {
        Optional<SaleOrder> saleSearched = saleOrderRepository.findById(id);
        return saleSearched.map(SaleOrder::toDto);
    }
}