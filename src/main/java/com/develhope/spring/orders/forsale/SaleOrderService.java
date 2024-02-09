package com.develhope.spring.orders.forsale;

import com.develhope.spring.orders.StatusPayment;
import com.develhope.spring.user.UserDTO;
import com.develhope.spring.user.UserEntity;
import com.develhope.spring.user.UserRepository;
import com.develhope.spring.vehicle.forsale.StatusSale;
import com.develhope.spring.vehicle.forsale.VehicleForSale;
import com.develhope.spring.vehicle.forsale.VehicleForSaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleOrderService {

    @Autowired
    SaleOrderRepository saleOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleForSaleRepository vehicleForSaleRepository;

    public SaleOrder createSaleOrder(SaleOrder saleOrder, Long vehicle_id, Long customer_id, Long seller_id) {
        VehicleForSale vehicle = vehicleForSaleRepository.findById(vehicle_id).get();

        if (vehicle.getStatus().equals(StatusSale.SELLABLE) || vehicle.getStatus().equals(StatusSale.ORDERABLE)) {
            SaleOrder order = new SaleOrder();
            order.setDate(LocalDate.now());

            //Calcola il totalPrice basato su listPrice e discountPercentage del veicolo
            double listPrice = vehicle.getListPrice().doubleValue();
            double discountPercentage = vehicle.getDiscountPercentage();
            double discount = (listPrice * discountPercentage) / 100;
            order.setTotalPrice(vehicle.getListPrice().subtract(BigDecimal.valueOf(discount)));

            order.setStatusPayment(saleOrder.getStatusPayment());
            order.setVehicleId(vehicle);
            order.setCustomerId(userRepository.findById(customer_id).get());
            order.setSellerId(userRepository.findById(seller_id).get());
            SaleOrder saveOrder = saleOrderRepository.saveAndFlush(order);

            //aggiorno stato del veicolo a NOT AVAILABLE
            /*vehicle.setStatus(StatusSale.NOT_AVAILABLE);
            vehicleForSaleRepository.save(vehicle);*/
            return saveOrder;
        } else {
            return null;
        }
    }

    public boolean payOrder(Long saleOrderId) {
        Optional<SaleOrder> orderToPay = saleOrderRepository.findById(saleOrderId);
        if (orderToPay.isPresent()) {
            SaleOrder order = orderToPay.get();
            order.setStatusPayment(StatusPayment.SETTLED);

            VehicleForSale vehicle = order.getVehicleId();
            vehicle.setStatus(StatusSale.NOT_AVAILABLE);
            vehicleForSaleRepository.saveAndFlush(vehicle);
            saleOrderRepository.saveAndFlush(order);
            return true;

        }
        return false;
    }

    /*public List<SaleOrderDTO> getAllsales() {
        List<SaleOrder> saleOrders = saleOrderRepository.findAll();
        return saleOrders.stream().map(SaleOrder::toDto).toList();
    }

    public Optional<SaleOrderDTO> getSalesById(Long id) {
        Optional<SaleOrder> saleSearched = saleOrderRepository.findById(id);
        return saleSearched.map(SaleOrder::toDto);
    }*/
}